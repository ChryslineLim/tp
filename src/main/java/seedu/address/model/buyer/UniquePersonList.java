package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.buyer.exceptions.DuplicatePersonException;
import seedu.address.model.buyer.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A buyer is considered unique by comparing using {@code Buyer#isSamePerson(Buyer)}. As such, adding and updating of
 * persons uses Buyer#isSamePerson(Buyer) for equality so as to ensure that the buyer being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a buyer uses Buyer#equals(Object) so
 * as to ensure that the buyer with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Buyer#isSamePerson(Buyer)
 */
public class UniquePersonList implements Iterable<Buyer> {

    private final ObservableList<Buyer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Buyer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent buyer as the given argument.
     */
    public boolean contains(Buyer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a buyer to the list.
     * The buyer must not already exist in the list.
     */
    public void add(Buyer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the buyer {@code target} in the list with {@code editedBuyer}.
     * {@code target} must exist in the list.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the list.
     */
    public void setPerson(Buyer target, Buyer editedBuyer) {
        requireAllNonNull(target, editedBuyer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedBuyer) && contains(editedBuyer)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedBuyer);
    }

    /**
     * Removes the equivalent buyer from the list.
     * The buyer must exist in the list.
     */
    public void remove(Buyer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code buyers}.
     * {@code buyers} must not contain duplicate buyers.
     */
    public void setPersons(List<Buyer> buyers) {
        requireAllNonNull(buyers);
        if (!personsAreUnique(buyers)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(buyers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Buyer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Buyer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code buyers} contains only unique buyers.
     */
    private boolean personsAreUnique(List<Buyer> buyers) {
        for (int i = 0; i < buyers.size() - 1; i++) {
            for (int j = i + 1; j < buyers.size(); j++) {
                if (buyers.get(i).isSamePerson(buyers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
