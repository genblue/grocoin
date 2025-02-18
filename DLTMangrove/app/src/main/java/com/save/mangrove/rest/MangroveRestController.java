package com.save.mangrove.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.save.mangrove.Mangrove;
import com.save.mangrove.Media;
import com.save.mangrove.Nursery;
import com.save.mangrove.Transactions;
import com.save.mangrove.UserData;

@RestController
public class MangroveRestController {

    @Autowired
    Mangrove mangrove;

    @GetMapping("/users")
    public List<UserData> fetchUsers() {
        return mangrove.listUsers();
    }

    @GetMapping("/txn")
    public List<Transactions> fetchTxns() {
        return mangrove.listTxns();
    }

    @PostMapping("/kyc")
    public Object performKyc(@RequestBody String id) throws Exception {
        /**
         * Step1 : To do the Kyc of the user
         * Step2 : On successful verification, generate public/private key
         * and expose it to user.
         * has to be done only once in lifetime.
         */
        return mangrove.createKeys(id);

    }

    @PostMapping("/txn")
    public boolean addTxn(@RequestBody Transactions txn) throws Exception{
        boolean status=mangrove.publishTxn(txn);
        return status;
    }
    
    
    @GetMapping("/testappstatus")
    public String fetchAppStatus() throws Exception{
        return "Yes,Mangrove app is running";
    }

    @GetMapping("/nurseries")
    public List<Nursery> fetchNurseries() {
        return mangrove.listNurseries();
    }

    @PostMapping("/nurseries")
    public void createNurseries(@RequestBody Nursery nursery) {
        mangrove.createNursery(nursery);

    }

    @GetMapping("/nursery/{id}")
    public Nursery fetchNurseryById(@PathVariable("id") int id) {
        return mangrove.fetchNurseryById(id);
    }
    @GetMapping("/media/{nurseryid}")
    public List<Media> fetchMediaByNurseryId(@PathVariable("nurseryid") int nurseryid) {
        return mangrove.getMediaByNurseryid(nurseryid);
    }
    
    @GetMapping("/medias")
    public List<Media> fetchMedias() {
        return mangrove.getMedias();
    }
    
    @GetMapping("/wallet/{nurseryid}")
    public List<Map<String,Object>> fetchWalletForNurseryId(@PathVariable("nurseryid") int nurseryid) {
        return mangrove.getWalletByNurseryid(nurseryid);
    }
    
    
}
