package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLER;

import seedu.address.logic.commands.FilterPropsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.AbstractFilterPropsPredicate;
import seedu.address.model.property.FilterPropsByPricePredicate;


/**
 * Parses user input to create a {@code FilterPropsCommand}.
 */
public class FilterPropsCommandParser extends Parser<FilterPropsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPropsCommand
     * and returns a FilterPropsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPropsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRICE_RANGE,
                PREFIX_CHARACTERISTICS, PREFIX_SELLER);

        if (areMoreThanOnePrefixesPresent(argMultimap, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_SELLER)
                || !isAnyPrefixPresent(argMultimap, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_SELLER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPropsCommand.MESSAGE_USAGE));
        }

        AbstractFilterPropsPredicate predicate = null;

        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
            predicate = new FilterPropsByPricePredicate(priceRange);
        }

        // TODO: Consider allowing filtering by multiple characteristics at once

        return new FilterPropsCommand(predicate);
    }
}
