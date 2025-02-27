package seedu.address.logic.parser.buyer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STRICT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.buyer.MatchBuyerCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MatchBuyerCommand object
 */
public class MatchBuyerCommandParser extends Parser<MatchBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MatchBuyerCommand
     * and returns a MatchBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MatchBuyerCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STRICT);
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            boolean isMatchingAll = arePrefixesPresent(argMultimap, PREFIX_STRICT);

            return new MatchBuyerCommand(index, isMatchingAll);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MatchBuyerCommand.MESSAGE_USAGE), pe);
        }

    }

}
