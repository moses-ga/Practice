package io.kenzo.learning.viewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardStats {
    private long totalUsers;
    private long activeUsers;
    private long inactiveUsers;
    private long newToday;
}
