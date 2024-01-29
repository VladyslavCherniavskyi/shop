package com.cherniavskyi.shop.repository.file.impl.local;

import com.cherniavskyi.shop.repository.file.ProductFileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("local")
public class LocalProductStorageRepositoryImpl extends AbstractLocalStorageRepository implements ProductFileStorageRepository {

    public LocalProductStorageRepositoryImpl(
            @Value("${local.directory.productDirectory}")
            String folderName) {
        super(folderName);
    }

}
