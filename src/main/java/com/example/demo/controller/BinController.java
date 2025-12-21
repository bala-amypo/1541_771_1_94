// package com.example.demo.controller;

// import com.example.demo.model.Bin;
// import com.example.demo.service.BinService;
// import org.springframework.web.bind.annotation.*;


// @RestController
// @RequestMapping("/api/bins")
// public class BinController {

//     private final BinService service;

//     public BinController(BinService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public Bin create(@RequestBody Bin bin) {
//         return service.createBin(bin);
//     }

//     @PutMapping("/{id}")
//     public Bin update(@PathVariable Long id, @RequestBody Bin bin) {
//         return service.updateBin(id, bin);
//     }

//     @GetMapping("/{id}")
//     public Bin getById(@PathVariable Long id) {
//         return service.getBinById(id);
//     }

//     @GetMapping
//     public List<Bin> getAll() {
//         return service.getAllBins();
//     }

//     @PutMapping("/{id}/deactivate")
//     public void deactivate(@PathVariable Long id) {
//         service.deactivateBin(id);
//     }
// }
