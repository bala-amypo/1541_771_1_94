// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.FillLevelRecord;
// import com.example.demo.service.FillLevelRecordService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// @RestController
// @RequestMapping("/api/fill-records")
// public class FillLevelRecordController {

//     private final FillLevelRecordService service;

//     public FillLevelRecordController(FillLevelRecordService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public FillLevelRecord create(@RequestBody FillLevelRecord record ) {
//         return service.createRecord(record);
//     }

//     @GetMapping("/{id}")
//     public FillLevelRecord get(@PathVariable Long id) {
//         return service.getRecordById(id);
//     }

//     @GetMapping("/bin/{binId}")
//     public List<FillLevelRecord> getBin(@PathVariable Long binId) {
//         return service.getRecordsForBin(binId);
//     }

//     // @GetMapping("/bin/{binId}/recent")
//     // public List<FillLevelRecord> getRecent(
//     //         @PathVariable Long binId,
//     //         @RequestParam(defaultValue = "5") int limit) {
//     //     return service.getRecentRecords(binId, limit);
//     // }
// }
