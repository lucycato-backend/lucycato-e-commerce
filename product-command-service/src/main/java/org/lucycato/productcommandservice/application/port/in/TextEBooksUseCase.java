package org.lucycato.productcommandservice.application.port.in;

import org.lucycato.productcommandservice.application.port.in.command.DeleteTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTextEBookCommand;
import org.lucycato.productcommandservice.domain.TextEBookDetail;

public interface TextEBooksUseCase {

    TextEBookDetail registerTextEBook(RegisterTextEBookCommand command);

    TextEBookDetail modifyTextEBook(ModifyTextEBookCommand command);

    void deleteTextEBook(DeleteTextEBookCommand command);
}
