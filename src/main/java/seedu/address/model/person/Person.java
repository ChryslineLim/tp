package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.address.Address;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;


/**
 * Represents a Person in the address book.
 * Guarantees: field values are validated, immutable.
 * Only priceRange and desiredCharacteristics may be null.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Optional<PriceRange> priceRange;
    private final Optional<Characteristics> desiredCharacteristics;
    private final Priority priority;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  PriceRange priceRange, Characteristics characteristics, Priority priority) {
        requireAllNonNull(name, phone, email, address, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.priceRange = Optional.ofNullable(priceRange);
        this.desiredCharacteristics = Optional.ofNullable(characteristics);
        this.priority = priority;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<PriceRange> getPriceRange() {
        return this.priceRange;
    }

    public Optional<Characteristics> getDesiredCharacteristics() {
        return this.desiredCharacteristics;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPriceRange().equals(getPriceRange())
                && otherPerson.getDesiredCharacteristics().equals(getDesiredCharacteristics())
                && otherPerson.getPriority().equals(getPriority());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Budget: ")
                .append(getPriceRange().map(PriceRange::toString).orElse("Budget: Not Specified"))
                .append("; Desired Characteristics: ")
                .append(getDesiredCharacteristics()
                        .map(Characteristics::toString)
                        .orElse("Not Specified"));

        builder.append(" Priority: ")
                .append(getPriority());

        return builder.toString();
    }

}
