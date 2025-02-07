package com.example.memocrud.controller;

import com.example.memocrud.dto.MemoRequestDto;
import com.example.memocrud.dto.MemoResponseDto;
import com.example.memocrud.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> saveMemo(@RequestBody MemoRequestDto requestDto) {
        return ResponseEntity.ok(memoService.saveMemo(requestDto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memoService.findById(id));
    }

    @PutMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.updateMemo(id, dto));
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }
}
