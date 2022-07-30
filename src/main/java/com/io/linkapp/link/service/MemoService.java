package com.io.linkapp.link.service;

import com.io.linkapp.exception.MemoNotFoundException;
import com.io.linkapp.link.domain.Memo;
import com.io.linkapp.link.repository.MemoRepository;
import java.util.List;
import java.util.UUID;

import com.io.linkapp.request.MemoRequest;
import com.io.linkapp.response.MemoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponse findMemoById(UUID id){
        Memo memo = memoRepository.findById(id)
                .orElseThrow(MemoNotFoundException::new);

        return new MemoResponse(memo);
    }

    public void save(MemoRequest memoRequest){
        Memo memo = Memo.builder()
                .articleId(memoRequest.getArticleId())
                .content(memoRequest.getContent())
                .build();

        memoRepository.save(memo);
    }

    public List<Memo> findAllMemo(){
        return memoRepository.findAll();
    }

    public void deleteMemo(UUID id){
        Memo memo = memoRepository.findById(id)
                .orElseThrow(MemoNotFoundException::new);

        memoRepository.delete(memo);
    }

    public void editMemo(UUID uuid, MemoRequest memoRequest) {
        Memo memo = memoRepository.findById(uuid)
                .orElseThrow(MemoNotFoundException::new);

        memo.edit(memoRequest.getContent());
        memoRepository.save(memo);
    }
}