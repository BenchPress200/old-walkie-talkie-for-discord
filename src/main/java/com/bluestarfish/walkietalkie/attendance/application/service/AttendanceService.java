package com.bluestarfish.walkietalkie.attendance.application.service;

import com.bluestarfish.walkietalkie.attendance.application.dto.UserRanking;
import com.bluestarfish.walkietalkie.attendance.domain.Attendance;
import com.bluestarfish.walkietalkie.attendance.infrastructure.AttendanceRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService extends ListenerAdapter {
    private final JDA jda;
    private final AttendanceRepository attendanceRepository;

    @Value("${discord.bot.attendance-channel-id}")
    private String channelId;

    @Value("${discord.bot.server-id}")
    private String serverId;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String str = event.getMessage().getContentDisplay().trim();
        Member member = event.getMember();
        String receivedChannelId = event.getChannel().getId();

        if (receivedChannelId.equals(channelId)) {
            switch (str) {
                case "출첵":
                case "출석체크":
                case "출쳌":
                case "출석쳌":
                case "출석첵":
                case "출석":
                case "ㅊㅊ":
                case "출근":
                case "출근체크":
                case "출근첵":
                case "출근쳌":
                case "출근띠":
                case "출근띠예":
                    event.getChannel().sendMessage(checkIn(member)).queue();
                    break;

                default:
                    break;
            }
        }
    }

    private String checkIn(Member member) {
        LocalDate today = LocalDate.now();
        Optional<Attendance> existingAttendance = attendanceRepository.findByUserIdAndCheckInDate(member.getId(),
                today);
        StringBuilder br = new StringBuilder("🚀 치직... **" + member.getEffectiveName() + "** 님");

        if (existingAttendance.isPresent()) {
            br.append("은 이미 오늘 출근을 완료했습니다. 치직... 🚀");
        } else {
            Attendance attendance = new Attendance(member.getId(), today);
            attendanceRepository.save(attendance);
            br.append(" 출근 완료입니다. 공부 시작 하세요. 오바. 치지직... 🚀");
        }
        return br.toString();
    }

    @Scheduled(cron = "0 0 8 * * MON", zone = "Asia/Seoul")
    public void weeklyRanking() {
        Guild guild = jda.getGuildById(serverId);
        LocalDate oneWeekAgo = LocalDate.now().minusDays(7);

        // 7일 출석한 유저 모두 가져오기
        List<UserRanking> topUsersWithSevenDays = attendanceRepository.findTopUsersWithSevenDays(oneWeekAgo);

        // 출석 일수가 7일 미만인 상위 10명의 유저 가져오기
        List<UserRanking> topUsersLessThanSevenDays = attendanceRepository.findTopUsersLessThanSevenDays(oneWeekAgo);

        List<UserRanking> rankings = new ArrayList<>();
        rankings.addAll(topUsersWithSevenDays);
        rankings.addAll(topUsersLessThanSevenDays);

        rankings.forEach(user -> user.setNickname(guild.getMemberById(user.getUserId()).getEffectiveName()));
        StringBuilder message = new StringBuilder("📊 **이번 주 출근 랭킹 상위 유저들입니다!** 📊\n\n");

        if (rankings.isEmpty()) {
            message.append("아무도 없네요...? 다들 공부 합시다! \n");
        } else {
            if (!topUsersWithSevenDays.isEmpty()) {
                message.append("🚀 일주일 내내 출근하신 분들입니다! 축하해주세요! 🚀\n");
                for (int i = 0; i < topUsersWithSevenDays.size(); i++) {
                    UserRanking ranking = rankings.get(i);
                    message.append("**")
                            .append(ranking.getNickname())
                            .append("** - 출근 횟수 : ")
                            .append(ranking.getAttendanceCount())
                            .append("\n");
                }
                message.append("\n🚀 이번주는 당신도 7일 출근 가능합니다! 화이팅! 🚀\n\n");
            }

            message.append("🚀 꾸준하게 출근한 상위 10명입니다! 축하합니다!! 🚀\n");
            for (int i = topUsersWithSevenDays.size(); i < rankings.size(); i++) {
                UserRanking ranking = rankings.get(i);
                message.append((i + 1))
                        .append(". **")
                        .append(ranking.getNickname())
                        .append("** - 출근 횟수 : ")
                        .append(ranking.getAttendanceCount())
                        .append("\n");
            }
            message.append("🚀 이번주도 다같이 힘내봐요! 화이팅!! 🚀\n\n");
        }

        jda.getTextChannelById(channelId).sendMessage(message).queue();
    }
}
