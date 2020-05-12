package com.testing.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.Admin;
import com.testing.repository.AdminRepository;
import com.testing.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Override
	public Admin getAdminById(Long id) {
		
		return adminRepository.findById(id).orElse(null);
	}

}
