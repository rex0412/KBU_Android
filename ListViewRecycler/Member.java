package com.example.listviewrecycler;

public class Member {
    String name;
    String birthYear;
    String job;
    int memberAssetId;

    public Member(String name, String birthYear, String job, int memberAssetId) {
        this.name = name;
        this.birthYear = birthYear;
        this.job = job;
        this.memberAssetId = memberAssetId;
    }

    public String getName() { return name; }
    public String getJob() { return job; }
    public int getMemberAssetId() { return memberAssetId; }

    public int getAge() {
        try {
            int year = Integer.parseInt(birthYear);
            return 2025 - year + 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}