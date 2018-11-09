package com.jdbc.application.dao;

import com.jdbc.application.model.Admin;
import com.jdbc.application.model.Journal;
import com.jdbc.application.model.Reader;
import com.jdbc.application.service.JdbcUtils;

import java.sql.*;
import java.util.List;
import java.util.Set;

/**
 * @author Bekh Artem
 * class implements all methods from CommonDao
 * and extends AbstractDaoJdbc to get connection with DB
 */
public class CommonDaoJdbc extends AbstractDaoJdbc implements CommonDao {
    //////////////QUERIES//////////////////
    private static final String SELECT_ALL_JOURNALS_BY_TITLE_ALPHABETIC="SELECT * FROM journal AS j ORDER BY j.title ASC";
    private static final String SELECT_ALL_JOURNALS_BY_TITLE_REVERSE="SELECT * FROM journal AS j ORDER BY j.title DESC";
    private static final String SELECT_ALL_JOURNALS_BY_PRICE_TO_SMALLER="SELECT * FROM journal AS j ORDER BY j.price DESC";
    private static final String SELECT_ALL_JOURNALS_BY_PRICE_TO_BIGGER="SELECT * FROM journal AS j ORDER BY j.price ASC";
    private static final String SELECT_ALL_JOURNALS="SELECT * FROM journal";
    private static final String SELECT_DISTINCT_TOPICS="SELECT DISTINCT topic FROM journal";
    private static final String SELECT_ALL_JOURNALS_BY_TOPIC_WITHOUT_SORTING="SELECT * FROM journal AS j WHERE j.topic=?";
    private static final String SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_ALPHABETIC="SELECT * FROM journal AS j WHERE j.topic=? ORDER BY title ASC";
    private static final String SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_REVERSE="SELECT * FROM journal AS j WHERE j.topic=? ORDER BY title DESC";
    private static final String SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_BIGGER="SELECT * FROM journal AS j WHERE j.topic=? ORDER BY price ASC";
    private static final String SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_SMALLER="SELECT * FROM journal AS j WHERE j.topic=? ORDER BY price DESC";
    private static final String SELECT_ALL_JOURNAL_READER="SELECT * FROM journal AS j WHERE j.id IN (SELECT id_journal FROM journal_reader WHERE id_reader=?)";
    private static final String SELECT_ALL__AVAILABLE_JOURNALS_FOR_READER="SELECT * FROM journal AS j WHERE id NOT IN (SELECT j.id FROM journal,journal_reader AS jr where j.id=jr.id_journal and jr.id_reader=?)";
    private static final String SELECT_JOURNAL_BY_TITLE="SELECT * FROM journal AS j WHERE j.title=?";
    private static final String SELECT_JOURNAL_BY_ID="SELECT * FROM journal AS j WHERE j.id=?";
    private static final String SELECT_ALL_READERS="SELECT * FROM reader";
    private static final String SELECT_READER="SELECT * FROM reader WHERE login=? AND password=?";
    private static final String SELECT_READER_BY_ID="SELECT * FROM reader WHERE id=?";
    private static final String SELECT_ADMIN="SELECT * FROM admin WHERE login=? AND password=?";
    private static final String UPDATE_READERS_ACCESS="UPDATE reader SET reader.access=? WHERE reader.id=?";
    private static final String UPDATE_READER_UNVERIFIED_BALANCE="UPDATE reader SET reader.unverifiedBalance=? WHERE id=?";
    private static final String UPDATE_READER_VERIFIED_BALANCE="UPDATE reader SET reader.verifiedBalance=? WHERE reader.id=?";
    private static final String UPDATE_JOURNAL_BY_ID="UPDATE journal AS j SET j.title=?,j.topic=?,j.price=? WHERE j.id=?";
    private static final String DELETE_JOURNAL_BY_ID="DELETE FROM journal WHERE id=?";
    private static final String INSERT_NEW_READER="INSERT INTO schema_final_project.reader(name,surname,login,password,email,access)" + "VALUES(?,?,?,?,?,?)";
    private static final String INSERT_NEW_JOURNAL="INSERT INTO schema_final_project.journal(title,topic,price)" + "VALUES(?,?,?)";
    private static final String INSERT_NEW_READER_JOURNAL="INSERT INTO schema_final_project.journal_reader(id_reader,id_journal)" + "VALUES(?,?)";
    //////////////EndOfQueries/////////////////////////////////
    @Override
    public Set<Reader> selectAllReaders() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_READERS);
            conn.commit();
            return new ReaderExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_READERS",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public Reader selectReader(String login, String password) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_READER);
            st.setString(1,login);
            st.setString(2,password);
            rs=st.executeQuery();
            if (!rs.next()){
                return null;
            }
            conn.commit();
            return new ReaderExtractor().extract(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_READER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public Reader selectReaderById(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_READER_BY_ID);
            st.setInt(1,id);
            rs=st.executeQuery();
            if (!rs.next()){
                return null;
            }
            conn.commit();
            return new ReaderExtractor().extract(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute READER_BY_ID",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public Admin selectAdmin(String login, String password) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ADMIN);
            st.setString(1,login);
            st.setString(2,password);
            rs=st.executeQuery();
            if (!rs.next()){
                return null;
            }
            conn.commit();
            return new AdminExtractor().extract(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ADMIN",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }
    @Override
    public List<Journal> selectAllJournalsByTitleAlphabetic() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_JOURNALS_BY_TITLE_ALPHABETIC);
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_JOURNALS_BY_TITLE_ALPHABETIC",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTitleReverse() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_JOURNALS_BY_TITLE_REVERSE);
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_JOURNALS_BY_TITLE_REVERSE",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByPriceToSmaller() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_JOURNALS_BY_PRICE_TO_SMALLER);
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_JOURNALS_BY_PRICE_TO_SMALLER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }


    @Override
    public List<Journal> selectAllJournals() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_JOURNALS);
            conn.commit();
            return  new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_JOURNALS",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<String> selectDistinctTopics() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_DISTINCT_TOPICS);
            conn.commit();
            return  new TopicExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_DISTINCT_TOPICS",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByPriceToBigger() throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st = conn.createStatement();
            rs=st.executeQuery(SELECT_ALL_JOURNALS_BY_PRICE_TO_BIGGER);
            conn.commit();
            return  new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL_JOURNALS_BY_PRICE_TO_BIGGER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTopicWithoutSorting(String topic) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNALS_BY_TOPIC_WITHOUT_SORTING);
            st.setString(1,topic);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNALS_BY_TOPIC_WITHOUT_SORTING",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTopicSortingAlphabetic(String topic) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_ALPHABETIC);
            st.setString(1,topic);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNALS_BY_TOPIC_SORTING_ALPHABETIC",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTopicSortingReverse(String topic) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_REVERSE);
            st.setString(1,topic);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNALS_BY_TOPIC_SORTING_REVERSE",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTopicSortingPriceToBigger(String topic) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_BIGGER);
            st.setString(1,topic);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_BIGGER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllJournalsByTopicSortingPriceToSmaller(String topic) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_SMALLER);
            st.setString(1,topic);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNALS_BY_TOPIC_SORTING_PRICE_TO_SMALLER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllReaderJournal(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL_JOURNAL_READER);
            st.setInt(1,id);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNAL_READER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectAllAvailableJournalsForReader(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_ALL__AVAILABLE_JOURNALS_FOR_READER);
            st.setInt(1,id);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_ALL__AVAILABLE_JOURNALS_FOR_READER",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<Journal> selectJournalByTitle(String title) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_JOURNAL_BY_TITLE);
            st.setString(1,title);
            rs=st.executeQuery();
            conn.commit();
            return new JournalExtractor().extractAll(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNAL_BY_TITLE",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public Journal selectJournalById(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(SELECT_JOURNAL_BY_ID);
            st.setInt(1,id);
            rs=st.executeQuery();
            if (!rs.next()){
                return null;
            }
            conn.commit();
            return new JournalExtractor().extract(rs);
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SELECT_JOURNAL_BY_ID",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void insertNewReader(String name, String surname, String login, String password, String email,boolean access) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conn.prepareStatement(INSERT_NEW_READER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);
            preparedStatement.setBoolean(6, access);
            preparedStatement.executeUpdate();
            conn.commit();
        }
        catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute INSERT_NEW_READER",e);
        }
        finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void updateReaderAccess(boolean access, int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(UPDATE_READERS_ACCESS);
            st.setBoolean(1,access);
            st.setInt(2,id);
            st.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute UPDATE_READERS_ACCESS",e);
        }finally {
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void updateJournalById(String title, String topic, int price,int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        try {
            st=conn.prepareStatement(UPDATE_JOURNAL_BY_ID);
            st.setString(1,title);
            st.setString(2,topic);
            st.setInt(3,price);
            st.setInt(4,id);
            st.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute UPDATE_JOURNAL_BY_ID",e);
        }finally {
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void updateReaderUnverifiedBalance(int unverifiedBalance, int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        conn.setAutoCommit(false);
        PreparedStatement st=null;
        ResultSet rs=null;
        Reader reader=null;
        int balance=0;
        PreparedStatement preparedStatement=null;
        try {
            st=conn.prepareStatement(SELECT_READER_BY_ID);
            st.setInt(1,id);
            rs=st.executeQuery();
            if (rs.next()) {
                reader= new   ReaderExtractor().extract(rs);
            }
            balance=reader.getUnverifiedBalance()+unverifiedBalance;
            preparedStatement=conn.prepareStatement(UPDATE_READER_UNVERIFIED_BALANCE);
            preparedStatement.setInt(1,balance);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute UPDATE_READER_UNVERIFIED_BALANCE",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void updateReaderVerifiedBalance(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        conn.setAutoCommit(false);
        PreparedStatement st=null;
        PreparedStatement st1=null;
        ResultSet rs=null;
        Reader reader=null;

        int balance=0;
        PreparedStatement preparedStatement=null;
        try {
            st=conn.prepareStatement(SELECT_READER_BY_ID);
            st.setInt(1,id);
            rs=st.executeQuery();
            if (rs.next()) {
                reader= new   ReaderExtractor().extract(rs);
            }
         int unverifiedBalance=reader.getUnverifiedBalance();
         int verifiedBalance=reader.getVerifiedBalance();
         verifiedBalance=verifiedBalance+unverifiedBalance;
         unverifiedBalance=0;
            st1=conn.prepareStatement(UPDATE_READER_UNVERIFIED_BALANCE);
            st1.setInt(1,unverifiedBalance);
            st1.setInt(2,id);
            st1.executeUpdate();
            preparedStatement=conn.prepareStatement(UPDATE_READER_VERIFIED_BALANCE);
            preparedStatement.setInt(1,verifiedBalance);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute UPDATE_READER_VERIFIED_BALANCE",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void deleteJournalById(int id) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            st=conn.prepareStatement(DELETE_JOURNAL_BY_ID);
            st.setInt(1,id);
            st.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute DELETE_JOURNAL_BY_ID",e);
        }finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(st);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void insertNewJournal(String title, String topic, int price) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conn.prepareStatement(INSERT_NEW_JOURNAL);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, topic);
            preparedStatement.setInt(3, price);
            preparedStatement.executeUpdate();
            conn.commit();
        }
        catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute INSERT_NEW_JOURNAL",e);
        }
        finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void subscribeToJournal(int id_reader, int id_journal) throws SQLException, DBSystemException {
        Connection conn=getSerializableConnection();
        PreparedStatement preparedStatement=null;
        PreparedStatement preparedStatement1=null;
        PreparedStatement preparedStatement2=null;
        PreparedStatement preparedStatement3=null;
        ResultSet rs=null;
        Reader reader=null;
    Journal journal=null;
            try {
                preparedStatement = conn.prepareStatement(SELECT_READER_BY_ID);
                preparedStatement.setInt(1, id_reader);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    reader = new ReaderExtractor().extract(rs);
                }
                int verifiedBalance=reader.getVerifiedBalance();
                preparedStatement1 = conn.prepareStatement(SELECT_JOURNAL_BY_ID);
                preparedStatement1.setInt(1, id_journal);
                rs = preparedStatement1.executeQuery();
                if (rs.next()) {
                    journal = new JournalExtractor().extract(rs);
                }
                int price=journal.getPrice();
                verifiedBalance=verifiedBalance-price;
                preparedStatement2=conn.prepareStatement(UPDATE_READER_VERIFIED_BALANCE);
                preparedStatement2.setInt(1,verifiedBalance);
                preparedStatement2.setInt(2,id_reader);
                preparedStatement2.executeUpdate();
                preparedStatement3=conn.prepareStatement(INSERT_NEW_READER_JOURNAL);
                preparedStatement3.setInt(1,id_reader);
                preparedStatement3.setInt(2,id_journal);
                preparedStatement3.executeUpdate();
                conn.commit();
            }

        catch (SQLException e){
            JdbcUtils.rollbackQuietly(conn);
            throw new DBSystemException("Can't execute SUBSCRIBE_ON_JOURNAL",e);
        }
        finally {
                JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(conn);
        }
    }
}
