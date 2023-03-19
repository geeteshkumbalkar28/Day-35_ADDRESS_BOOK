import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressBookServiceTest {
    @Before
    public void init() {
        System.out.println("Welcome to AddressBook Management Service");
    }
    @Test
    public void givenAddressBookDB_WhenRetrivedShouldMatchPersonCount() {
        AddressBookService addressBookService = new AddressBookService();
        List<Person> addressBookData = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(0, addressBookData.size());
    }

}