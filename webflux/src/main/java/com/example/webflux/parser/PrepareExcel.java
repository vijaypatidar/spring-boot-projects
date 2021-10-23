package com.example.webflux.parser;

import com.example.webflux.models.Note;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PrepareExcel {
    public Mono<byte[]> notesToExcelFile(Flux<Note> noteFlux) {
        Mono<List<Note>> buffer = Mono.from(noteFlux.buffer());
        return buffer.mapNotNull(notes -> {
            Workbook workbook = new HSSFWorkbook();
            Sheet notedSheet = workbook.createSheet("Notes");
            prepareHeader(notedSheet);
            prepareRow(notedSheet, notes);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private void prepareHeader(Sheet notedSheet) {
        Row headerRow = notedSheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("TITLE");
        headerRow.createCell(2).setCellValue("CREATED DATE");
        headerRow.createCell(3).setCellValue("Description");
    }

    private void prepareRow(Sheet notedSheet, List<Note> notes) {
        notes.forEach(note -> {
            Row row = notedSheet.createRow(notes.indexOf(note) + 1);
            row.createCell(0).setCellValue(note.getId());
            row.createCell(1).setCellValue(note.getTitle());
            row.createCell(2).setCellValue(note.getCreatedOn().toString());
            row.createCell(3).setCellValue(note.getDescription());
        });
    }

}
