package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.AssetDTO;
import sprint_1.model.Asset;
import sprint_1.model.AssetDetail;
import sprint_1.model.MeetingRoom;
import sprint_1.service.AssetDetailService;
import sprint_1.service.AssetService;
import sprint_1.service.MeetingRoomService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @Autowired
    AssetDetailService assetDetailService;

    @Autowired
    MeetingRoomService meetingRoomService;

    @GetMapping()
    public ResponseEntity<List<Asset>> listAssets() {
        List<Asset> assets = assetService.findAll();
        if (assets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        int total = 0;
        for (Asset asset: assets) {
            for (AssetDetail element: asset.getAssetDetailCollection()) {
                if (element != null) {
                    total += Integer.parseInt(element.getAssetQuantity());
                } else {
                    total = 0;
                }
            }
            asset.setUsingQuantity(String.valueOf(total));
            total = 0;
        }
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Asset> check(@PathVariable Long id) {
        Asset asset = assetService.findById(id);
        if (asset != null) {
            return new ResponseEntity<>(asset, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@RequestBody AssetDTO assetDTO){
        Asset asset = new Asset();
        asset.setAssetName(assetDTO.getAssetName());
        asset.setUsingQuantity(assetDTO.getUsingQuantity());
        asset.setFixingQuantity(assetDTO.getFixingQuantity());
        asset.setTotal(assetDTO.getTotal());
        asset.setImage(assetDTO.getImage());
        asset.setDescription(assetDTO.getDescription());
        asset.setPrice(assetDTO.getPrice());
        assetService.save(asset);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
