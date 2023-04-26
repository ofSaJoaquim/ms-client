package br.com.branetlogistica.msclient.modules.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.msclient.modules.database.model.DataBase;


public interface DataBaseRepository extends JpaRepository<DataBase, Long> {

}