package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.AssetDetailDTO;
import sprint_1.model.AssetDetail;
import sprint_1.service.AssetDetailService;
import sprint_1.service.AssetService;
import sprint_1.service.MeetingRoomService;

@RestController
@CrossOrigin
@RequestMapping("/assetDetail")
public class AssetDetailController {
    @Autowired
    private AssetDetailService assetDetailService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("create")
    public ResponseEntity<Void> createAssetDetail(@RequestBody AssetDetailDTO assetDetailDTO) {
        AssetDetail assetDetail = new AssetDetail();
        assetDetail.setAsset(assetService.findByAssetName(assetDetailDTO.getNameAssetDetail()));
        assetDetail.setMeetingRoomAsset(meetingRoomService.findByRoomName(assetDetailDTO.getNameMeetingRoom()));
        assetDetail.setAssetQuantity(assetDetailDTO.getQuantity());
        assetDetailService.save(assetDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
