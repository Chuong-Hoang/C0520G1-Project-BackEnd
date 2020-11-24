package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.AssetDTO;
import sprint_1.dto.AssetDetailDTO;
import sprint_1.model.Asset;
import sprint_1.model.AssetDetail;
import sprint_1.service.AssetDetailService;
import sprint_1.service.AssetService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * AssetController
 * <p>
 * Version 1.0
 * <p>
 * Date: 22-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 22-11-2020         TungTS            CRUD
 */

@RestController
@CrossOrigin
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;
    @Autowired
    AssetDetailService assetDetailService;

    /**
     * get data for Asset List page
     *
     * @param
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Asset>> listAssets() {
        List<Asset> assets = assetService.findAll();
        if (assets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        int totalUsingQuantity = 0;
        for (Asset asset : assets) {
            for (AssetDetail assetDetail : asset.getAssetDetailCollection()) {
                if (assetDetail != null) {
                    totalUsingQuantity += Integer.parseInt(assetDetail.getAssetQuantity());
                } else {
                    totalUsingQuantity = 0;
                }
            }
            asset.setUsingQuantity(String.valueOf(totalUsingQuantity));
            totalUsingQuantity = 0;
        }
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    /**
     * search assets by assetName containing
     *
     * @param inputSearch
     * @return
     */
    @GetMapping("/inputSearch")
    public ResponseEntity<List<Asset>> searchAssets(@RequestParam("valueSearch") String inputSearch) {
        List<Asset> assets = assetService.findAllByAssetNameContaining(inputSearch);
        if (assets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    /**
     * get data for Asset Detail page
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<AssetDTO> check(@PathVariable Long id) {
        Asset asset = assetService.findById(id);
        List<AssetDetail> assetDetails = (List<AssetDetail>) asset.getAssetDetailCollection();
        List<AssetDetailDTO> assetDetailsDTO = new ArrayList<>();
        if (asset != null) {
            for (AssetDetail element : assetDetails) {
                AssetDetailDTO assetDetailDTO = new AssetDetailDTO();
                assetDetailDTO.setNameMeetingRoom(element.getMeetingRoomAsset().getRoomName());
                assetDetailDTO.setQuantity(element.getAssetQuantity());
                assetDetailsDTO.add(assetDetailDTO);
            }
            AssetDTO assetDTO = new AssetDTO(asset.getIdAsset(), asset.getAssetName(),
                    asset.getUsingQuantity(), asset.getFixingQuantity(), asset.getTotal(),
                    asset.getImage(), asset.getDescription(), asset.getPrice(), assetDetailsDTO);
            return new ResponseEntity<>(assetDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * create asset
     *
     * @param assetDTO
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@Valid @RequestBody AssetDTO assetDTO) {
        if (assetService.existsByAssetName(assetDTO.getAssetName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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

    /**
     * edit asset by idAsset
     *
     * @param assetDTO
     * @param id
     * @return
     */
    @PatchMapping(value = "/edit/{id}")
    public ResponseEntity<Asset> updateAsset(@Valid @RequestBody AssetDTO assetDTO, @PathVariable Long id) {
        System.err.println("Updating " + id);
        Asset asset = assetService.findById(id);
        if (asset == null) {
            System.out.println("id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        asset.setAssetName(assetDTO.getAssetName());
        asset.setUsingQuantity(assetDTO.getUsingQuantity());
        asset.setFixingQuantity(assetDTO.getFixingQuantity());
        asset.setTotal(assetDTO.getTotal());
        asset.setImage(assetDTO.getImage());
        asset.setDescription(assetDTO.getDescription());
        asset.setPrice(assetDTO.getPrice());

        assetService.save(asset);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    /**
     * delete asset by idAsset
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable Long id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assetService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
