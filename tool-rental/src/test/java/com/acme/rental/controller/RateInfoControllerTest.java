package com.acme.rental.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class RateInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateRentalChargeSceneOne() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"JAKR\", \"rentalDays\": 5, \"discountPercent\": 101, \"checkoutDate\": \"2015-09-03\" }";
        JSONObject jsonResponse = invokeAndGetNotOkResponse(jsonRequest);

        Assert.isTrue(jsonResponse == null, "Response should be null");
    }

    @Test
    void testCalculateRentalChargeSceneTwo() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"LADW\", \"rentalDays\": 3, \"discountPercent\": 10, \"checkoutDate\": \"2020-07-02\" }";
        JSONObject jsonResponse = invokeAndGetOkResponse(jsonRequest);

        Assert.isTrue("LADW".equals(jsonResponse.getString("toolCode")), "Tool code should be LADW");
        Assert.isTrue("Ladder".equals(jsonResponse.getString("toolType")), "Tool type should be Ladder");
        Assert.isTrue("Werner".equals(jsonResponse.getString("toolBrand")), "Tool brand should be Werner");
        Assert.isTrue(3 == jsonResponse.getInt("rentalDays"), "Rental days should be 3");
        Assert.isTrue("07-02-20".equals(jsonResponse.getString("checkoutDate")), "Checkout date should be 07-02-20");
        Assert.isTrue("07-05-20".equals(jsonResponse.getString("dueDate")), "Due date should be 07-05-20");
        Assert.isTrue("$1.99".equals(jsonResponse.getString("dailyRentalCharge")),
                "Daily rental charge should be $1.99");
        Assert.isTrue(3 == jsonResponse.getInt("chargeDays"), "Charge days should be 3");
        Assert.isTrue("$5.97".equals(jsonResponse.getString("preDiscountCharge")),
                "Pre-discount charge should be $5.97");
        Assert.isTrue("10%".equals(jsonResponse.getString("discountPercent")), "Discount percent should be 10%");
        Assert.isTrue("$0.60".equals(jsonResponse.getString("discountAmount")), "Discount amount should be $0.60");
        Assert.isTrue("$5.37".equals(jsonResponse.getString("finalCharge")), "Final charge should be $5.37");
    }

    @Test
    void testCalculateRentalChargeSceneThree() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"CHNS\", \"rentalDays\": 5, \"discountPercent\": 25, \"checkoutDate\": \"2015-07-02\" }";
        JSONObject jsonResponse = invokeAndGetOkResponse(jsonRequest);

        Assert.isTrue("CHNS".equals(jsonResponse.getString("toolCode")), "Tool code should be CHNS");
        Assert.isTrue("Chainsaw".equals(jsonResponse.getString("toolType")), "Tool type should be Chainsaw");
        Assert.isTrue("Stihler".equals(jsonResponse.getString("toolBrand")), "Tool brand should be Stihler");
        Assert.isTrue(5 == jsonResponse.getInt("rentalDays"), "Rental days should be 5");
        Assert.isTrue("07-02-15".equals(jsonResponse.getString("checkoutDate")), "Checkout date should be 07-02-15");
        Assert.isTrue("07-07-15".equals(jsonResponse.getString("dueDate")), "Due date should be 07-07-15");
        Assert.isTrue("$1.49".equals(jsonResponse.getString("dailyRentalCharge")),
                "Daily rental charge should be $1.49");
        Assert.isTrue(3 == jsonResponse.getInt("chargeDays"), "Charge days should be 3");
        Assert.isTrue("$4.47".equals(jsonResponse.getString("preDiscountCharge")),
                "Pre-discount charge should be $4.47");
        Assert.isTrue("25%".equals(jsonResponse.getString("discountPercent")), "Discount percent should be 25%");
        Assert.isTrue("$1.12".equals(jsonResponse.getString("discountAmount")), "Discount amount should be $1.12");
        Assert.isTrue("$3.35".equals(jsonResponse.getString("finalCharge")), "Final charge should be $3.35");
    }

    @Test
    void testCalculateRentalChargeSceneFour() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"JAKD\", \"rentalDays\": 6, \"discountPercent\": 0, \"checkoutDate\": \"2015-09-03\" }";
        JSONObject jsonResponse = invokeAndGetOkResponse(jsonRequest);

        Assert.isTrue("JAKD".equals(jsonResponse.getString("toolCode")), "Tool code should be JAKD");
        Assert.isTrue("Jackhammer".equals(jsonResponse.getString("toolType")), "Tool type should be Jackhammer");
        Assert.isTrue("DeWalt".equals(jsonResponse.getString("toolBrand")), "Tool brand should be DeWalt");
        Assert.isTrue(6 == jsonResponse.getInt("rentalDays"), "Rental days should be 6");
        Assert.isTrue("09-03-15".equals(jsonResponse.getString("checkoutDate")), "Checkout date should be 09-03-15");
        Assert.isTrue("09-09-15".equals(jsonResponse.getString("dueDate")), "Due date should be 09-09-15");
        Assert.isTrue("$2.99".equals(jsonResponse.getString("dailyRentalCharge")),
                "Daily rental charge should be $2.99");
        Assert.isTrue(4 == jsonResponse.getInt("chargeDays"), "Charge days should be 4");
        Assert.isTrue("$11.96".equals(jsonResponse.getString("preDiscountCharge")),
                "Pre-discount charge should be $11.96");
        Assert.isTrue("0%".equals(jsonResponse.getString("discountPercent")), "Discount percent should be 0%");
        Assert.isTrue("$0.00".equals(jsonResponse.getString("discountAmount")), "Discount amount should be $0.00");
        Assert.isTrue("$11.96".equals(jsonResponse.getString("finalCharge")), "Final charge should be $11.96");
    }

    @Test
    void testCalculateRentalChargeSceneFive() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"JAKR\", \"rentalDays\": 9, \"discountPercent\": 0, \"checkoutDate\": \"2015-07-02\" }";
        JSONObject jsonResponse = invokeAndGetOkResponse(jsonRequest);

        Assert.isTrue("JAKR".equals(jsonResponse.getString("toolCode")), "Tool code should be JAKR");
        Assert.isTrue("Jackhammer".equals(jsonResponse.getString("toolType")), "Tool type should be Jackhammer");
        Assert.isTrue("Ridgid".equals(jsonResponse.getString("toolBrand")), "Tool brand should be Ridgid");
        Assert.isTrue(9 == jsonResponse.getInt("rentalDays"), "Rental days should be 9");
        Assert.isTrue("07-02-15".equals(jsonResponse.getString("checkoutDate")), "Checkout date should be 07-02-15");
        Assert.isTrue("07-11-15".equals(jsonResponse.getString("dueDate")), "Due date should be 07-11-15");
        Assert.isTrue("$2.99".equals(jsonResponse.getString("dailyRentalCharge")),
                "Daily rental charge should be $2.99");
        Assert.isTrue(7 == jsonResponse.getInt("chargeDays"), "Charge days should be 7");
        Assert.isTrue("$20.93".equals(jsonResponse.getString("preDiscountCharge")),
                "Pre-discount charge should be $20.93");
        Assert.isTrue("0%".equals(jsonResponse.getString("discountPercent")), "Discount percent should be 0%");
        Assert.isTrue("$0.00".equals(jsonResponse.getString("discountAmount")), "Discount amount should be $0.00");
        Assert.isTrue("$20.93".equals(jsonResponse.getString("finalCharge")), "Final charge should be $20.93");
    }

    @Test
    void testCalculateRentalChargeSceneSix() throws Exception {

        String jsonRequest = "{ \"toolCode\": \"JAKR\", \"rentalDays\": 4, \"discountPercent\": 50, \"checkoutDate\": \"2020-07-02\" }";
        JSONObject jsonResponse = invokeAndGetOkResponse(jsonRequest);

        Assert.isTrue("JAKR".equals(jsonResponse.getString("toolCode")), "Tool code should be JAKR");
        Assert.isTrue("Jackhammer".equals(jsonResponse.getString("toolType")), "Tool type should be Jackhammer");
        Assert.isTrue("Ridgid".equals(jsonResponse.getString("toolBrand")), "Tool brand should be Ridgid");
        Assert.isTrue(4 == jsonResponse.getInt("rentalDays"), "Rental days should be 4");
        Assert.isTrue("07-02-20".equals(jsonResponse.getString("checkoutDate")), "Checkout date should be 07-02-20");
        Assert.isTrue("07-06-20".equals(jsonResponse.getString("dueDate")), "Due date should be 07-06-20");
        Assert.isTrue("$2.99".equals(jsonResponse.getString("dailyRentalCharge")),
                "Daily rental charge should be $2.99");
        Assert.isTrue(2 == jsonResponse.getInt("chargeDays"), "Charge days should be 2");
        Assert.isTrue("$5.98".equals(jsonResponse.getString("preDiscountCharge")),
                "Pre-discount charge should be $5.98");
        Assert.isTrue("50%".equals(jsonResponse.getString("discountPercent")), "Discount percent should be 50%");
        Assert.isTrue("$2.99".equals(jsonResponse.getString("discountAmount")), "Discount amount should be $2.99");
        Assert.isTrue("$2.99".equals(jsonResponse.getString("finalCharge")), "Final charge should be $2.99");
    }

    private JSONObject invokeAndGetOkResponse(String jsonRequest) throws Exception {
        MvcResult result = mockMvc.perform(get("/rental/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        return new JSONObject(content);
    }

    private JSONObject invokeAndGetNotOkResponse(String jsonRequest) throws Exception {
        mockMvc.perform(get("/rental/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());

        return null;
    }
}
