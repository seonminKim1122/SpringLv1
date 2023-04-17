package com.sparta.springlv1.service;

import com.sparta.springlv1.dto.MemoRequestDto;
import com.sparta.springlv1.dto.MemoResponseDto;
import com.sparta.springlv1.entity.Memo;
import com.sparta.springlv1.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return new MemoResponseDto(memo);
    }

    public List<MemoResponseDto> getAllMemo() {
        List<Memo> memoList = memoRepository.findAll();
        return memoList.stream().map(MemoResponseDto::new).sorted((memo1, memo2) -> memo2.getModifiedAt().compareTo(memo1.getModifiedAt())).collect(Collectors.toList());
    }
}