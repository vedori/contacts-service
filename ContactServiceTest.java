package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import contact.Contact;
import contact.ContactService;

class ContactServiceTest {

	@Test
	void testContactServiceAddUniqueId() {
		assertAll(() -> {
			assertDoesNotThrow(() -> {
				for (int i = 0; i < 100_000; i++) {
					ContactService.addContact(new Contact(ContactService.generateUniqueId(), "John", "Doe"));
					ContactService.addContact(new Contact(ContactService.generateUniqueId(), "John", "Doe"));
				}
			});
		}, () -> {
			assertDoesNotThrow(() -> {
				ContactService.addContact(new Contact("ID1", "John", "Doe", "1234567890", "12 Test Drive."));
				ContactService.addContact(new Contact("ID2", "John", "Doe", "1234567890", "12 Test Drive."));
			});
		});

	}

	@Test
	void testContactServiceAddSameId() {
		assertThrows(IllegalArgumentException.class, () -> {
			ContactService.addContact(new Contact("AAA", "John", "Doe", "1234567890", "12 Test Drive."));
			ContactService.addContact(new Contact("AAA", "Nick", "Moe"));
		});
	}

	@Test
	void testContactServiceDeleteContactById() {
		assertDoesNotThrow(() -> {
			ContactService.addContact(new Contact("BBB", "John", "Doe"));
			ContactService.deleteContact("BBB");
			ContactService.addContact(new Contact("BBB", "John", "Doe"));
		});
	}

	@Test
	void testContactServiceUpdateFields() throws Exception {
		ContactService.addContact(new Contact("XXX", "XXX", "XXX", "0000000000", "XXX XXX XXX."));
		assertAll(() -> {
			ContactService.updateFirstName("XXX", "Jane");
			assertEquals(ContactService.getContact("XXX").orElseThrow().getFirstName(), "Jane");
		}, () -> {
			ContactService.updateLastName("XXX", "Smith");
			assertEquals(ContactService.getContact("XXX").orElseThrow().getLastName(), "Smith");
		}, () -> {
			ContactService.updatePhone("XXX", "1234567890");
			assertEquals(ContactService.getContact("XXX").orElseThrow().getPhone(), "1234567890");
		}, () -> {
			ContactService.updateAddress("XXX", "200 Gold St.");
			assertEquals(ContactService.getContact("XXX").orElseThrow().getAddress(), "200 Gold St.");
		});
	}

	@Test
	void testContactServiceUpdateFieldsByIncorrectId() {
		assertAll(() -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateFirstName("update", "Jane");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateLastName("update", "Smith");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updatePhone("update", "1112223334");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateAddress("update", "200 Gold Street.");
			});
		});
	}

	@Test
	void testContactServiceUpdateFieldsByNullId() {
		assertAll(() -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateFirstName(null, "Jane");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateLastName(null, "Smith");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updatePhone(null, "1112223334");
			});
		}, () -> {
			assertThrows(IllegalArgumentException.class, () -> {
				ContactService.updateAddress(null, "200 Gold Street.");
			});
		});
	}

}
