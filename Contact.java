package contact;

import java.util.HashSet;

public class Contact {
	private static final int ID_LENGTH = 10;
	private static final int NAME_LENGTH = 10;
	private static final int PHONE_LENGTH = 10;
	private static final int ADDRESS_LENGTH = 30;
	private static final String INIT_PHONE = "1234567890";
	private static final String INIT_ADDRESS = "123 Test Drive.";
	private static HashSet<String> ids = new HashSet<String>();

	private final String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;

	public Contact(String id, String firstName, String lastName, String phone, String address) throws Exception {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(address);
		if (id == null || ids.contains(id) || id.length() > ID_LENGTH) {
			throw new IllegalArgumentException("Invalid id!");
		}
		this.id = id;
		ids.add(id);
	}

	public Contact(String id, String firstName, String lastName, String phone) throws Exception {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(INIT_ADDRESS);
		if (id == null || ids.contains(id) || id.length() > ID_LENGTH) {
			throw new IllegalArgumentException("Invalid id!");
		}
		this.id = id;
		ids.add(id);
	}

	public Contact(String id, String firstName, String lastName) throws Exception {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(INIT_PHONE);
		setAddress(INIT_ADDRESS);
		if (id == null || ids.contains(id) || id.length() > ID_LENGTH) {
			throw new IllegalArgumentException("Invalid id!");
		}
		this.id = id;
		ids.add(id);
	}

	public final String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() > NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid first name!");
		}
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() > NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid last name!");
		}
		this.lastName = lastName;
	}

	public final String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null || phone.length() != PHONE_LENGTH || !phone.matches("\\d*")) {
			throw new IllegalArgumentException("Invalid phone number!");
		}
		this.phone = phone;
	}

	public final String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null || address.length() > ADDRESS_LENGTH) {
			throw new IllegalArgumentException("Invalid address!");
		}
		this.address = address;
	}

	public final String getId() {
		return id;
	}

	protected static HashSet<String> getAllIds() {
		return ids;
	}

	@Override
	public String toString() {
		return "ID=" + id + ": " + firstName + " " + lastName + " Phone=" + phone + "\n" + address;
	}
}
