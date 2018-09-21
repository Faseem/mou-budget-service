package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.ReleaseFrequency;
import lk.dialog.mou.web.service.releaseFrequency.ReleaseFrequencyService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/releaseFrequency")
public class ReleaseFrequencyController {

    @Autowired
    private ReleaseFrequencyService releaseFrequencyService;

    @EnableAudit(description = "Saving ReleaseFrequency")
    @PostMapping(value = "/saveReleaseFrequency")
    ResponseEntity<APIResponse> saveReleaseFrequency(@RequestBody ReleaseFrequency releaseFrequency,
                                          @RequestHeader(value = "user") String user) {
        return ResponseBuilder.build(
                ResponseBuilder.success(releaseFrequencyService.addReleaseFrequency(releaseFrequency))
        );
    }

    @EnableAudit(description = "getReleaseFrequencyExists")
    @PostMapping(value = "/getReleaseFrequencyExists")
    ResponseEntity<APIResponse> getReleaseFrequencyExists(@RequestBody ReleaseFrequency releaseFrequency,
                                               @RequestHeader(value = "user") String user) {
        return ResponseBuilder.build(
                ResponseBuilder.success(releaseFrequencyService.getReleaseFrequencyByKeys(releaseFrequency))
        );
    }
}
