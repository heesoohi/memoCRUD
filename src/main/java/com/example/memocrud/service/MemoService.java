package com.example.memocrud.service;

import com.example.memocrud.dto.MemoRequestDto;
import com.example.memocrud.dto.MemoResponseDto;
import com.example.memocrud.entity.Memo;
import com.example.memocrud.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponseDto saveMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto.getContent());
        Memo savedmemo = memoRepository.save(memo);
        return new MemoResponseDto(savedmemo.getId(), savedmemo.getContent());
    }

    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for (Memo memo : memos) {
            memoResponseDtos.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }

        return memoResponseDtos;
    }

    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public MemoResponseDto updateMemo(Long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다")
        );
        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public void deleteMemo(Long id) {
        if(!memoRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 아이디에 맞는 메모 없음");
        }
        memoRepository.deleteById(id);
    }
}
