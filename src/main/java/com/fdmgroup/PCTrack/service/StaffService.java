package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import org.springframework.data.domain.Sort;


@Service
public class StaffService {
	private StaffRepository staffRepo;
	
	public StaffService(StaffRepository staffRepo) {
		super();
		this.staffRepo = staffRepo;
		
	}
	
	public List<Staff> findAllStaffs() {
		return this.staffRepo.findAllByOrderByUserUsernameAsc();
	}
	
	// pagnates staff
	public Page<Staff> getStaffPage(int pageNumber, int pageSize) {
		 Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("user.username").ascending());
        return this.staffRepo.findAll(pageable);
    }
	
	// pagenates staff with query match 
	public Page<Staff> findAllUsersPartialMatch(String query, int pageNumber, int pageSize) {
		   Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return this.staffRepo.findPartial(query, pageable);
	}
	
	public long staffCount() {
		return this.staffRepo.count();
	}
	
	public void deleteById(int id) {
		if (this.staffRepo.existsById(id)) {
			staffRepo.deleteById(id);
		} else {
			throw new RuntimeException("Staff not found");
		}
	}

	public long staffCountPartial(String query) {
		return this.staffRepo.countByUsernameLike(query);
	}
	public Staff findByStaffId(int staffId) {
		return this.staffRepo.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found."));
	}
	
	public void save(Staff newStaff) {
		if (this.staffRepo.existsById(newStaff.getStaffId())) {
			throw new RuntimeException("Staff already exists");
		} else {
			this.staffRepo.save(newStaff);
		}
	}
	
	
	public List<Staff> findByUserId(int userId) {
		return this.staffRepo.findByUserId(userId);
	}
	
	public void deleteByStaffId(int userId) {
		if (this.staffRepo.existsById(userId)) {
			staffRepo.deleteById(userId);
			
		} else {
			throw new RuntimeException("Staff does not exist");
		}
	}
	
	public void update(Staff newStaff) {
		if (this.staffRepo.existsById(newStaff.getStaffId())) {
			this.staffRepo.save(newStaff);
		
		} else {
			throw new RuntimeException("Staff does not exist");
		}
	}
	
	public List<Room> getRoomsStaffIsAdmin(int userId) {
	
		return this.staffRepo.findRoomsStaffIsAdmin(userId);
	}
	
	public List<Room> getRoomStaffIsRegisterdIn (int userId) {
		return this.staffRepo.findRoomsStaffIsRegisteredIn(userId);
	}
}
