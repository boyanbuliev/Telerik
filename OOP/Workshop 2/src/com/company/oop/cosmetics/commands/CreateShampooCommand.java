package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;

import java.util.List;

public class CreateShampooCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
