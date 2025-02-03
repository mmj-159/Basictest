package com.example.testjdbc.service;

import com.example.testjdbc.dto.MemoRequestDto;
import com.example.testjdbc.dto.MemoResponseDto;
import com.example.testjdbc.entity.Memo;
import com.example.testjdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {

        Memo memo = new Memo(dto.getContent()); // 저장 되기 전 Memo
        Memo savedMemo = memoRepository.save(memo);// 저장된 Memo
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        //리턴 타입을 맞추기 위한 dto 리스트 그릇
        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos){
            MemoResponseDto dto = new MemoResponseDto(memo.getId(),memo.getContent());
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 ID가 존재하지 않음")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public MemoResponseDto update(Long id, MemoRequestDto dto){
        Memo updatedMemo = memoRepository.updateContent(id, dto.getContent());
        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    public void deleteById(Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 ID 존재하지 않음")
        );
    }
}
