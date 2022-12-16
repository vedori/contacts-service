package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import contact.Contact;

class ContactTest {

	@Test
	void testContact() throws Exception {
		Contact contact = new Contact("ID-123", "John", "Doe", "1234567890", "12 Test Drive.");
		assertEquals(contact.getId(), "ID-123");
		assertEquals(contact.getFirstName(), "John");
		assertEquals(contact.getLastName(), "Doe");
		assertEquals(contact.getPhone(), "1234567890");
		assertEquals(contact.getAddress(), "12 Test Drive.");
	}

	@Test
	void testContactIdIsUnique() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-999", "John", "Doe");
			new Contact("ID-999", "Dan", "Smith");
		});
	}

	@Test
	void testContactIdIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "John", "Doe");
		});
	}

	@Test
	void testContactIdTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-1234567890", "John", "Doe");
		});
	}

	@Test
	void testContactFirstNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-001", "ABCDEFGHIJK", "Doe");
		});
	}

	@Test
	void testContactFirstNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-002", null, "Doe");
		});
	}

	@Test
	void testContactLastNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-003", "John", "ABCDEFGHIJKLMNOP");
		});
	}

	@Test
	void testContactLastNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-004", "John", null);
		});
	}

	@Test
	void testContactPhoneTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-005", "John", "Doe", "12345678901");
		});
	}

	@Test
	void testContactPhoneTooShort() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-006", "John", "Doe", "123456789");
		});
	}

	@Test
	void testContactPhoneIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-007", "John", "Doe", null);
		});
	}

	@Test
	void testContactPhoneHasOnlyDigits() {
		assertAll(() -> {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "John", "Doe", "12345g7890");
			});
		}, () -> {
			assertDoesNotThrow(() -> {
				new Contact("999", "John", "Doe", "1234567890");
			});
		});
	}

	@Test
	void testContactAddressTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-008", "John", "Doe", "1234567890",
					"1234567890123456789 ABCDEFGHIJKLMNOPABCDEFGHIJKLMNOP Drive.");
		});
	}

	@Test
	void testContactAddressIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("ID-009", "John", "Doe", "1234567890", null);
		});
	}

}
