package com.bluestarfish.walkietalkie.attendance.infrastructure;

import com.bluestarfish.walkietalkie.attendance.application.dto.UserRanking;
import com.bluestarfish.walkietalkie.attendance.domain.Attendance;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByUserIdAndCheckInDate(String userId, LocalDate checkInDate);

    @Query("SELECT new com.bluestarfish.walkietalkie.attendance.application.dto.UserRanking(a.userId, COUNT(a)) " +
            "FROM Attendance a " +
            "WHERE a.checkInDate >= :oneWeekAgo " +
            "GROUP BY a.userId " +
            "ORDER BY COUNT(a) DESC")
    List<UserRanking> findTopRankingsSince(@Param("oneWeekAgo") LocalDate oneWeekAgo);

    @Query("SELECT new com.bluestarfish.walkietalkie.attendance.application.dto.UserRanking(a.userId, COUNT(a)) " +
            "FROM Attendance a " +
            "WHERE a.checkInDate >= :oneWeekAgo " +
            "GROUP BY a.userId " +
            "HAVING COUNT(a) = 7")
    List<UserRanking> findTopUsersWithSevenDays(@Param("oneWeekAgo") LocalDate oneWeekAgo);

    @Query("SELECT new com.bluestarfish.walkietalkie.attendance.application.dto.UserRanking(a.userId, COUNT(a)) " +
            "FROM Attendance a " +
            "WHERE a.checkInDate >= :oneWeekAgo " +
            "GROUP BY a.userId " +
            "HAVING COUNT(a) < 7 " +
            "ORDER BY COUNT(a) DESC " +
            "LIMIT 10")
    List<UserRanking> findTopUsersLessThanSevenDays(@Param("oneWeekAgo") LocalDate oneWeekAgo);
}
