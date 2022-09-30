package com.io.linkapp.link.service;

import com.io.linkapp.exception.CustomGlobalException;
import com.io.linkapp.exception.ErrorCode;
import com.io.linkapp.link.domain.Inquiry;
import com.io.linkapp.link.repository.InquiryRepository;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {
    
    private final InquiryRepository repository;
    
    /**
     * 리스트 조회
     * @param search
     * @return
     */
    public List<Inquiry> getList(Predicate search){
        return (List<Inquiry>) repository.findAll(search);
    }
    
    /**
     * 페이징 조회
     *
     * @param search 검색 조건
     * @param page   페이징 조건
     * @return 검색된 목록
     */
    public Page<Inquiry> getPage(Predicate search, Pageable page) {
        return repository.findAll(search, page);
    }
    
    /**
     * 상세 조회
     * @param id
     * @return
     */
    public Inquiry get(UUID id){
        return repository.findInquiryById(id);
    }
    
    /**
     * (1:1 문의사항) 등록
     * @param inquiry
     * @return
     */
    public Inquiry add(Inquiry inquiry){
        return repository.save(inquiry);
    }
    
    /**
     * 문의 사항 및 답변 수정
     * @param id
     * @param inquiry
     * @return
     */
    public Inquiry modify(UUID id, Inquiry inquiry){
        if(inquiry == null) return null;
        
        Inquiry out = repository.findInquiryById(id);
        
        if(out == null){
            throw new CustomGlobalException(ErrorCode.INQUIRY_NOT_FOUND);
        }else if(out.getIsAnswered()){
            throw new CustomGlobalException(ErrorCode.INQUIRY_ALREADY_ANSWERED);
        }
        
        out.setInquiryTitle(inquiry.getInquiryTitle());
        out.setInquiry(inquiry.getInquiry());
        out.setAnswerTitle(inquiry.getAnswerTitle());
        out.setAnswer(inquiry.getAnswer());
        
        
        return out;
    }
    
    /**
     * (1:1 문의사항) 답변 여부
     * @param
     * @return
     */
    public Inquiry setIsAnswered(UUID id, Boolean answered){
        Inquiry inquiry = repository.getById(id);
        inquiry.setAnswered(answered);
        return repository.save(inquiry);
    }
    
    /**
     * 삭제
     * @param id
     */
    public void remove(UUID id){
        repository.deleteById(id);
    }
    
    public void set( ){
        Inquiry inquiry = Inquiry.builder().inquiryTitle("title")
            .userId(UUID.randomUUID())
            .inquiry("inquiry")
            .answerTitle("title")
            .answer("answer")
            .isAnswered(false)
            .build();
        
        Inquiry inquiry2 = Inquiry.builder().inquiryTitle("title2")
            .userId(UUID.randomUUID())
            .inquiry("inquiry2")
            .answerTitle("title2")
            .answer("answer2")
            .isAnswered(false)
            .build();
        
        Inquiry inquiry3 = Inquiry.builder().inquiryTitle("title3")
            .userId(UUID.randomUUID())
            .inquiry("inquiry3")
            .answerTitle("title3")
            .answer("answer3")
            .isAnswered(false)
            .build();
        
        Inquiry inquiry4 = Inquiry.builder().inquiryTitle("title4")
            .userId(UUID.randomUUID())
            .inquiry("inquiry4")
            .answerTitle("title4")
            .answer("answer4")
            .isAnswered(false)
            .build();
        
        Inquiry newInquiry = add(inquiry);
        Inquiry newInquiry2 = add(inquiry2);
        Inquiry newInquiry3 = add(inquiry3);
        Inquiry newInquiry4 = add(inquiry4);
        
        
    }

}
