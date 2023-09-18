package com.deadgrandead.testcontainers.realizations;

import com.deadgrandead.testcontainers.interfaces.SystemProfile;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}