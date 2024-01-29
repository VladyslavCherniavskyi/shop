package com.cherniavskyi.shop.repository.file.impl.local;

import com.cherniavskyi.shop.repository.file.UserFileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("local")
public class LocalUserStorageRepositoryImpl extends AbstractLocalStorageRepository implements UserFileStorageRepository {

    public LocalUserStorageRepositoryImpl(
            @Value("${local.directory.userDirectory}")
            String folderName) {
        super(folderName);
    }

}
