package be.pxl.student.mapper;

import be.pxl.student.entity.Account;

public class CounterAccountMapper {
        public static Account map(String[] splitInput) {

            //Check if splitInput has enough fields
            if (splitInput.length != 7) {
                throw new IllegalArgumentException("Input should contain 7 fields");
            }

            //Make account
            Account account = new Account();
            account.setName(null);
            account.setIBAN(splitInput[2]);

            return account;
        }
}
