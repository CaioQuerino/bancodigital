package br.com.querino.bancodigital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Transfer extends Transaction {

	@ManyToOne
	@JoinColumn(name = "origin_account_id", nullable = false)
	private AccountEntity originAccount;

	@ManyToOne
	@JoinColumn(name = "destination_account_id", nullable = false)
	private AccountEntity destinationAccount;
}