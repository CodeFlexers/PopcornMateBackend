//package com.popcornmate.config;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.transaction.TransactionSystemException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.util.stream.Collectors;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // 1. 잘못된 요청 (예: @RequestParam 타입 불일치)
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("요청 파라미터 타입이 잘못되었습니다.");
//    }
//
//    // 2. 필수 요청값 누락 (예: @RequestParam(required=true)인데 없음)
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<String> handleMissingParam(MissingServletRequestParameterException ex) {
//        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("필수 요청 파라미터가 누락되었습니다: " + ex.getParameterName());
//    }
//
//    // 3. @Valid 검증 실패
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
//                .map(e -> e.getField() + ": " + e.getDefaultMessage())
//                .collect(Collectors.joining(", "));
//        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("요청 데이터 오류: " + errorMessage);
//    }
//
//    // 4. DTO 변환 실패 (예: @RequestBody -> 객체 변환 실패)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleMessageNotReadable(HttpMessageNotReadableException ex) {
//        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("요청 형식이 잘못되었습니다. : "+ex.getMessage());
//    }
//
//    // 5. 엔티티 없음
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("대상을 찾을 수 없습니다.");
//    }
//
//    // 6. 데이터 무결성 위반 (제약조건 오류, FK 오류 등)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<String> handleIntegrity(DataIntegrityViolationException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("데이터 제약 조건 위반");
//    }
//
//    // 7. 트랜잭션 내부 유효성 검증 오류
//    @ExceptionHandler(TransactionSystemException.class)
//    public ResponseEntity<String> handleTransaction(TransactionSystemException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("트랜잭션 처리 중 오류 발생");
//    }
//
//    // 8. 405 Method Not Allowed
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).contentType(MediaType.APPLICATION_JSON).body("지원하지 않는 HTTP 메서드입니다.");
//    }
//
//    // 9. 모든 Runtime 예외 (위에 안 걸리는 일반 로직 오류)
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body("서버 오류: " + ex.getMessage());
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex){
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body("권한이 없습니다. : " + ex.getMessage());
//    }
//
//    // 10. 최상위 예외 처리 (모든 예외의 마지막 보루)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        ex.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body("알 수 없는 오류 발생");
//    }
//
//
//}

