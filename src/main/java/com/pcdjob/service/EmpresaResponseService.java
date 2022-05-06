package com.pcdjob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcdjob.controller.dto.AreaDTO;
import com.pcdjob.controller.dto.EmpresaSalvaDTO;
import com.pcdjob.controller.dto.response.ResponseEmailEmpresa;
import com.pcdjob.controller.dto.response.ResponseEnderecoEmpresa;
import com.pcdjob.controller.dto.response.ResponseTelefoneEmpresa;
import com.pcdjob.model.empresa.EmailEmpresa;
import com.pcdjob.model.empresa.EmpresaEntity;
import com.pcdjob.model.empresa.EnderecoEmpresa;
import com.pcdjob.model.empresa.TelefoneEmpresa;

@Service
public class EmpresaResponseService {
	public List<ResponseEmailEmpresa> converterEmail(EmpresaEntity empresa) {
		if(empresa.getEmailEmpresa().size() > 0) {
			int indice = 0;
			List<ResponseEmailEmpresa> emailEmpresaList = new ArrayList<>();
			while(indice < empresa.getEmailEmpresa().size() && indice < 2) {
				EmailEmpresa emailEmpresa = empresa.getEmailEmpresa().get(indice);
				ResponseEmailEmpresa response = new ResponseEmailEmpresa(emailEmpresa.getId(), emailEmpresa.getEmail());
				emailEmpresaList.add(response);
				indice++;
			}
			return emailEmpresaList;
		} else {
			return null;
		}
	}
	
	public List<ResponseTelefoneEmpresa> converterTelefone(EmpresaEntity empresa) {
		if(empresa.getTelefoneEmpresa().size() > 0) {
			int indice = 0;
			List<ResponseTelefoneEmpresa> telefoneEmpresaList = new ArrayList<>();
			while(indice < empresa.getTelefoneEmpresa().size() && indice < 2) {
				TelefoneEmpresa telefoneEmpresa = empresa.getTelefoneEmpresa().get(indice);
				ResponseTelefoneEmpresa response = new ResponseTelefoneEmpresa(telefoneEmpresa.getId(), telefoneEmpresa.getNumero());
				telefoneEmpresaList.add(response);
				indice++;
			}
			return telefoneEmpresaList;
		} else {
			return null;
		}
	}
	
	public ResponseEnderecoEmpresa converterEndereco(EmpresaEntity empresa) {
		if(empresa.getEndereco() != null) {
			EnderecoEmpresa e = empresa.getEndereco();
			return new ResponseEnderecoEmpresa(e.getId(), e.getRua(), e.getNumero(), e.getCep(), e.getBairro(), 
							e.getCidade().getId(), e.getCidade().getCidade(), e.getCidade().getEstado().getId(), e.getCidade().getEstado().getEstado(), e.getCidade().getEstado().getSigla());
		} else {
			return null;
		}
	}
	
	public List<EmpresaSalvaDTO> listarEmpresas(List<EmpresaEntity> empresas) {
		int indice = 0;
		List<EmpresaSalvaDTO> dtoList = new ArrayList<>();
		while(empresas.size() > indice) {
			List<ResponseEmailEmpresa> emails = converterEmail(empresas.get(indice));
			List<ResponseTelefoneEmpresa> telefones = converterTelefone(empresas.get(indice));
			ResponseEnderecoEmpresa endereco = converterEndereco(empresas.get(indice));
			AreaDTO area = new AreaDTO(empresas.get(indice).getAreaAtuacao());
			EmpresaSalvaDTO dto = new EmpresaSalvaDTO(empresas.get(indice), emails, telefones, endereco, area);
			dtoList.add(dto);
			indice++;
		}
		return dtoList;
	}
	
	public Page<EmpresaSalvaDTO> paginarEmpresasDTO(List<EmpresaSalvaDTO> dtos, Pageable paginacao) {
		Page<EmpresaSalvaDTO> paginasDTO = new PageImpl<EmpresaSalvaDTO>(dtos, paginacao, dtos.size());
		return paginasDTO;
	}
}
