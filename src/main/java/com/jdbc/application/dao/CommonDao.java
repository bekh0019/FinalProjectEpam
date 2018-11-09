package com.jdbc.application.dao;

import com.jdbc.application.model.Admin;
import com.jdbc.application.model.Journal;
import com.jdbc.application.model.Reader;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author Bekh Artem
 * interface with all DAO methods used in this
 * application
 */
public interface CommonDao {
     Set<Reader> selectAllReaders() throws SQLException, DBSystemException;
    Reader selectReader(String login,String password) throws SQLException, DBSystemException;
     Reader selectReaderById(int id) throws SQLException, DBSystemException;
     Admin selectAdmin(String login, String password) throws SQLException, DBSystemException;
     List<Journal> selectAllJournalsByTitleAlphabetic() throws SQLException, DBSystemException;
     List<Journal> selectAllJournalsByTitleReverse() throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByPriceToBigger() throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByPriceToSmaller() throws SQLException, DBSystemException;
     List <Journal> selectAllJournals() throws SQLException, DBSystemException;
     List <String> selectDistinctTopics() throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByTopicWithoutSorting(String topic) throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByTopicSortingAlphabetic(String topic) throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByTopicSortingReverse(String topic) throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByTopicSortingPriceToBigger(String topic) throws SQLException, DBSystemException;
     List <Journal> selectAllJournalsByTopicSortingPriceToSmaller(String topic) throws SQLException, DBSystemException;
     List <Journal> selectAllReaderJournal(int id) throws SQLException, DBSystemException;
     List <Journal> selectAllAvailableJournalsForReader(int id) throws SQLException, DBSystemException;
     List<Journal> selectJournalByTitle(String title) throws SQLException, DBSystemException;
     Journal selectJournalById(int id) throws SQLException, DBSystemException;
     void insertNewReader(String name,String surname,String login,String password,String email,boolean access) throws SQLException, DBSystemException;
     void updateReaderAccess(boolean access,int id) throws SQLException, DBSystemException;
     void updateJournalById(String title,String topic,int price,int id) throws SQLException, DBSystemException;
     void updateReaderUnverifiedBalance(int unverifiedBalance,int id) throws SQLException, DBSystemException;
     void updateReaderVerifiedBalance(int id) throws SQLException, DBSystemException;
     void deleteJournalById(int id) throws SQLException, DBSystemException;
     void insertNewJournal(String title,String topic,int price) throws SQLException, DBSystemException;
     void subscribeToJournal(int id_reader,int id_journal) throws SQLException, DBSystemException;


}
