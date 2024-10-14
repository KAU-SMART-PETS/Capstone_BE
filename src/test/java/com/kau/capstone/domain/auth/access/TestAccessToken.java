package com.kau.capstone.domain.auth.access;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestAccessToken {
}
