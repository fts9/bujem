package uk.coles.ed.bujem.data_access;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import static uk.coles.ed.bujem.constant.Constant.AZURE_STORAGE_CONNECTION_STRING;
import static uk.coles.ed.bujem.constant.Constant.ENTRY_TABLE_NAME;

@Service
public class EntryDataAccessImpl implements EntryDataAccess {
    private final Logger logger = LoggerFactory.getLogger(EntryDataAccessImpl.class);

    private CloudTable entryTable;

    @PostConstruct
    public void init() {
        try {
            CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(AZURE_STORAGE_CONNECTION_STRING);
            CloudTableClient cloudTableClient = cloudStorageAccount.createCloudTableClient();
            entryTable = cloudTableClient.getTableReference(ENTRY_TABLE_NAME);
            entryTable.createIfNotExists();
        } catch(InvalidKeyException | URISyntaxException ex) {
            logger.error("There was an error accessing the Azure Storage account specified", ex);
        } catch (StorageException ex) {
            logger.error("There was an error trying to use the 'bj_entry' Azure Table", ex);
        }
    }
}
