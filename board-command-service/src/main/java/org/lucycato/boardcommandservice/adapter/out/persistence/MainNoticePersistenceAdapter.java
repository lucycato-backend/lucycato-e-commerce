package org.lucycato.boardcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.MainNoticeJapEntity;
import org.lucycato.boardcommandservice.adapter.out.persistence.repository.MainNoticeJapRepository;
import org.lucycato.boardcommandservice.application.port.out.MainNoticePort;
import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

import org.lucycato.boardcommandservice.application.port.out.result.MainBoardResult;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class MainNoticePersistenceAdapter implements MainNoticePort {

    private final MainNoticeJapRepository mainNoticeJapRepository;
    @Override
    public MainBoardResult CreateMainNotice(Long userId, String title, String content, String type) {
        MainNoticeJapEntity mainNoticeJapEntity = new MainNoticeJapEntity(userId, title, content, type);

        MainNoticeJapEntity savedMainNoticeJapEntity = mainNoticeJapRepository.save(mainNoticeJapEntity);
        return MainBoardResult.from(savedMainNoticeJapEntity);
    }
}
