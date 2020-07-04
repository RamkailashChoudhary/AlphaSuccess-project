package com.app.alphasucess.ui.tabui.test.adapters;

import java.util.List;

public class TestResultData {

    private String marks;
    private String rank;
    private boolean canReview;
    private List<LeaderboardData> leaderboard;

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isCanReview() {
        return canReview;
    }

    public void setCanReview(boolean canReview) {
        this.canReview = canReview;
    }

    public List<LeaderboardData> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<LeaderboardData> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
