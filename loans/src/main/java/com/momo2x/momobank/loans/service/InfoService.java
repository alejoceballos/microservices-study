package com.momo2x.momobank.loans.service;

import com.momo2x.momobank.loans.dto.BuildDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final BuildDto buildInfo;

    public BuildDto findInfo() {
        return buildInfo;
    }

}
