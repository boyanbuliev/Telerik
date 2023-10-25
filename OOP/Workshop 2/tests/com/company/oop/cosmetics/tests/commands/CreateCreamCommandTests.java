//package com.company.oop.cosmetics.tests.commands;
//
//import com.company.oop.cosmetics.commands.CreateCreamCommand;
//import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
//import com.company.oop.cosmetics.core.contracts.Command;
//import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
//import com.company.oop.cosmetics.models.contracts.Product;
//import com.company.oop.cosmetics.models.enums.GenderType;
//import com.company.oop.cosmetics.models.enums.ScentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static com.company.oop.cosmetics.tests.models.CreamTests.*;
//import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CreateCreamCommandTests {
//
//    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
//
//    Command createCreamCommand;
//    CosmeticsRepository cosmeticsRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//        cosmeticsRepository = new CosmeticsRepositoryImpl();
//        createCreamCommand = new CreateCreamCommand(cosmeticsRepository);
//    }
//
//    @Test
//    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
//        // Arrange
//        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
//
//        // Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(params));
//    }
//
//    @Test
//    public void should_ThrowException_When_PriceInvalid() {
//        //Arrange
//        List<String> parameters = List.of(
//                VALID_CREAM_NAME,
//                VALID_CREAM_BRAND_NAME,
//                "Invalid Price",
//                GenderType.MEN.toString(),
//                ScentType.LAVENDER.toString());
//        //Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
//    }
//
//    @Test
//    public void should_ThrowException_When_GenderInvalid() {
//        //Arrange
//        List<String> parameters = List.of(
//                VALID_CREAM_NAME,
//                VALID_CREAM_BRAND_NAME,
//                "10.75",
//                "Invalid Gender",
//                ScentType.LAVENDER.toString());
//        //Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
//    }
//
//    @Test
//    public void should_ThrowException_When_ScentInvalid() {
//        //Arrange
//        List<String> parameters = List.of(
//                VALID_CREAM_NAME,
//                VALID_CREAM_BRAND_NAME,
//                "10.75",
//                GenderType.MEN.toString(),
//                "Invalid Scent");
//        //Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
//    }
//
//    @Test
//    public void should_ThrowException_When_NameExists() {
//        //Arrange
//        Product testProduct = addInitializedCreamToRepository(cosmeticsRepository);
//
//        List<String> parameters = List.of(
//                testProduct.getName(),
//                VALID_CREAM_BRAND_NAME,
//                "10.75",
//                GenderType.MEN.toString(),
//                ScentType.LAVENDER.toString());
//
//        //Act, Assert
//        assertThrows(IllegalArgumentException.class, () -> createCreamCommand.execute(parameters));
//    }
//
//    @Test
//    public void should_Return_InitializedProduct() {
//        // Arrange, Act
//        List<String> parameters = List.of(
//                VALID_CREAM_NAME,
//                VALID_CREAM_BRAND_NAME,
//                "10.75",
//                GenderType.MEN.toString(),
//                ScentType.LAVENDER.toString());
//        createCreamCommand.execute(parameters);
//
//        // Assert
//        Product cream = cosmeticsRepository.findProductByName(VALID_CREAM_NAME);
//        assertAll(
//                () -> assertEquals(cream.getBrandName(), VALID_CREAM_BRAND_NAME),
//                () -> assertEquals(cream.getPrice(),10.75),
//                () -> assertEquals(cream.getGenderType(), GenderType.MEN)
//        );
//    }
//
//    @Test
//    public void should_AddProduct_ToList() {
//        // Arrange
//        List<String> parameters = List.of(
//                VALID_CREAM_NAME,
//                VALID_CREAM_BRAND_NAME,
//                "10.75",
//                GenderType.MEN.toString(),
//                ScentType.LAVENDER.toString());
//
//        //Act, Assert
//        assertAll(
//                () -> assertDoesNotThrow(() -> createCreamCommand.execute(parameters)),
//                () -> assertEquals(1, cosmeticsRepository.getProducts().size())
//        );
//    }
//}
