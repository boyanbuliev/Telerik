package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.commands.*;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CommandFactory;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.core.contracts.ProductRepository;

public class CommandFactoryImpl implements CommandFactory {

    private static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Command %s is not supported.";

    @Override
    public Command createCommandFromCommandName(String commandTypeValue, ProductRepository productRepository) {
        CommandType commandType = tryParseCommandType(commandTypeValue);

        switch (commandType) {
            case CREATECATEGORY:
                return new CreateCategoryCommand(productRepository);
            case CREATEPRODUCT:
                return new CreateProductCommand(productRepository);
            case ADDPRODUCTTOCATEGORY:
                return new AddProductToCategoryCommand(productRepository);
            case SHOWCATEGORY:
                return new ShowCategoryCommand(productRepository);
            default:
                // This exception means that the CommandType enum and command classes are inconsistent.
                // This exception is not handled because it indicates that code should be fixed.
                throw new UnsupportedOperationException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, commandTypeValue));
        }
    }

    private CommandType tryParseCommandType(String value) {
        try {
            return CommandType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // This exception means that the user has entered not existing command.
            throw new InvalidUserInputException(String.format(COMMAND_NOT_SUPPORTED_MESSAGE, value));
        }
    }

}
