package com.fdmgroup.PCTrack.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.dal.RoomAdminRepository;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;
import org.springframework.data.domain.Sort;

@Service
public class StaffService {
	private StaffRepository staffRepo;
	private MandateRepository mandateRepo;
	private RoomAdminRepository roomAdminRepo;

	public StaffService(StaffRepository staffRepo, MandateRepository mandateRepo, RoomAdminRepository roomAdminRepo) {
		super();
		this.staffRepo = staffRepo;
		this.mandateRepo = mandateRepo;
		this.roomAdminRepo = roomAdminRepo;
	}

	public List<Staff> findAllStaffs() {
		return this.staffRepo.findAllByOrderByUserUsernameAsc();
	}

	// get staff that is filtered by location and paginated.
	public List<Staff> getLocationAdminFilteredStaff(int pageNumber, int pageSize, List<Location> locations,
			List<AdminLevel> adminLevels) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("user.username").ascending());
		return this.staffRepo.findByLocationInAndAdminLevelIn(locations, adminLevels, pageable).getContent();
	}

	// pagnates staff
	public Page<Staff> getStaffPage(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("user.username").ascending());
		return this.staffRepo.findAll(pageable);
	}

	// pagenates staff with query match AND with the filter
	public List<Staff> findAllUsersPartialMatch(String query, int pageNumber, int pageSize, List<Integer> locations,
			List<Integer> adminLevels) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return this.staffRepo.findPartial(query, pageable, locations, adminLevels).getContent();
	}

	public long staffCount() {
		return this.staffRepo.count();
	}

	public void deleteById(int id) {
		if (this.staffRepo.existsById(id)) {
			Optional<RoomAdmin> roomAdminOptional = roomAdminRepo.findById(id);
			if (roomAdminOptional.isPresent()) {
				System.out.println("hello!!");
				RoomAdmin roomAdmin = roomAdminOptional.get();
				List<Mandate> mandates = mandateRepo.findByRoomAdmin(roomAdmin);

				// Delete mandates associated with the room admin
				for (Mandate mandate : mandates) {
					mandateRepo.delete(mandate);
				}
			}

			staffRepo.deleteById(id);
		} else {
			throw new RuntimeException("Staff not found");
		}
	}

	public long staffCountPartial(String query, List<Integer> locations, List<Integer> adminLevels) {
		return this.staffRepo.countByUsernameLike(query, locations, adminLevels);
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
		List<Room> returnList = this.staffRepo.findRoomsStaffIsAdmin(userId);
		returnList.addAll(this.staffRepo.findRoomAssignedByUserId(userId));
		return returnList;
	}

	public long staffCountFiltered(List<Location> locations, List<AdminLevel> adminLevels) {
		return this.staffRepo.countByLocationInAndAdminLevelIn(locations, adminLevels);
	}

//	public void assignLocationAdmin(int locationId, int userId) {
//		AdminLevel adminLevel = new AdminLevel("Location", 2);
//		Location location = this.locationRepository.findById(locationId).get();
//		User user = this.userRepository.findById(userId).get();
//		
//	}

	public List<Room> getRoomStaffIsRegisterdIn(int userId) {
		return this.staffRepo.findRoomsStaffIsRegisteredIn(userId);
	}

}
