package contact;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class ContactService {
	private static HashMap<String, Contact> contacts = new HashMap<String, Contact>();
	private static Supplier<IllegalArgumentException> invalidId = () -> {
		throw new IllegalArgumentException("Invalid contact id!");
	};

	static public void addContact(Contact contact) throws Exception {
		contacts.put(Optional.ofNullable(contact).orElseThrow(() -> {
			throw new IllegalArgumentException("Cannot add Task!");
		}).getId(), contact);
	}

	static public void deleteContact(String id) throws Exception {
		Optional.ofNullable(contacts.get(id)).orElseThrow(invalidId);
		Contact.getAllIds().remove(id);
		contacts.remove(id);
	}

	static public void updateFirstName(String id, String name) throws Exception {
		Optional<Contact> getContact = Optional.ofNullable(contacts.get(id));
		getContact.orElseThrow(invalidId).setFirstName(name);
	}

	static public void updateLastName(String id, String name) throws Exception {
		Optional<Contact> getContact = Optional.ofNullable(contacts.get(id));
		getContact.orElseThrow(invalidId).setLastName(name);
	}

	static public void updatePhone(String id, String phone) throws Exception {
		Optional<Contact> getContact = Optional.ofNullable(contacts.get(id));
		getContact.orElseThrow(invalidId).setPhone(phone);
	}

	static public void updateAddress(String id, String address) throws Exception {
		Optional<Contact> getContact = Optional.ofNullable(contacts.get(id));
		getContact.orElseThrow(invalidId).setAddress(address);
	}

	static public Optional<Contact> getContact(String id) {
		return Optional.ofNullable(contacts.get(id));
	}

	static public String generateUniqueId() {
		return UUID.randomUUID().toString().substring(0, 11).replaceFirst("-", "");
	}
}
