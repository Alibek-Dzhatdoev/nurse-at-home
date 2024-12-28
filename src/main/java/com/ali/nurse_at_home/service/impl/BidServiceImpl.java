package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.client.NotificationClient;
import com.ali.nurse_at_home.mapper.BidMapper;
import com.ali.nurse_at_home.model.dto.BidDto;
import com.ali.nurse_at_home.model.entity.Bid;
import com.ali.nurse_at_home.model.entity.Procedure;
import com.ali.nurse_at_home.model.params.BidParams;
import com.ali.nurse_at_home.repository.BidRepository;
import com.ali.nurse_at_home.repository.PatientRepository;
import com.ali.nurse_at_home.repository.ProcedureRepository;
import com.ali.nurse_at_home.service.BidService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.ali.nurse_at_home.utils.SecurityContextUtils.getUserIdFromToken;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BidServiceImpl implements BidService {

    BidMapper bidMapper;

    NotificationClient notificationClient;

    BidRepository bidRepository;
    PatientRepository patientRepository;
    ProcedureRepository procedureRepository;

    @Override
    public BidDto create(BidParams params) {
        val newBid = bidMapper.toBid(params);
        newBid.setPatientId(patientRepository.findByUserId(getUserIdFromToken())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента по токену"))
                .getId());

        List<Procedure> bidServices = procedureRepository.findAllById(params.getServiceIds());
        if (bidServices.size() < params.getServiceIds().size()) {
            throw new ResponseStatusException(BAD_REQUEST, "Укажите корректные ID услуг");
        }

        if (params.getScheduledTime() == null) {
            return createUrgentBid(newBid);
        } else {
            return createScheduledBid(newBid);
        }

        // а если заявка на конкретное время, то искать

//        if (newBid.getNurseId() == null) {
//            //TODO отправить уведомления всем медсестрам в округе, которые выполняют все указанные услуги
//        } else {
//            //TODO отправить уведомление конкретной медсестре
//        }
//        return null;
    }

    private BidDto createUrgentBid(Bid bid) {
        //TODO если заявка "как можно быстрее" то если нет ни одного ответа перевести в статус Rejected и отправить юзеру уведомление об этом, чтоб он если что повторил заявку
        if (bid.getNurseId() == null) {
            //TODO отправить уведомления всем медсестрам в округе отсортировав по дальности
            // значит надо
            // 1. Получить геопозицию пациента по его адресу (если в адресе нет, то из сервиса яндекса, и сохранить в адрес)
            // 2. найти всех медсестер в радиусе 10 км
            // 3. отфильтровать их так, чтобы:
            // - местоположение пациента входило в область поиска заказов медсестры
            // - сестра была доступна
            // - не была в ЧС у пациента,
            // - он не был в ЧС у медсестры
            // - медсестра бы исполняла указанную процедуру
            // 4. отсортировать их по отдаленности и отправлять каждой из них уведомление по очереди, ожидая 15 секунд на ответ.
            // 5. если никто не ответил, то поставить статус rejected и отправить уведомление пациенту
        } else {
            //TODO отправить уведомления сестрам в округе, но первой поставить указанную медсестру
        }
        return null;
    }

    private BidDto createScheduledBid(Bid bid) {
        //TODO если заявка ко времени то поставить ей статус SCHEDULED и создать шедулер, который в указанное время -10 минут вызовет логику отправки,
        // если без ответа перевести в статус Rejected и отправить юзеру уведомление об этом, чтоб он если что повторил заявку
        if (bid.getNurseId() == null) {
            //TODO отправить уведомления всем медсестрам в округе отсортировав по дальности
            // значит надо
            // 1. Получить геопозицию пациента по его адресу (если в адресе нет, то из сервиса яндекса, и сохранить в адрес)
            // 2. найти всех медсестер в радиусе 10 км
            // 3. отфильтровать их так, чтобы:
            // - местоположение пациента входило в область поиска заказов медсестры
            // - сестра была доступна
            // - не была в ЧС у пациента,
            // - он не был в ЧС у медсестры
            // - медсестра бы исполняла указанную процедуру
            // 4. отсортировать их по отдаленности и отправлять каждой из них уведомление по очереди, ожидая 15 секунд на ответ.
            // 5. если никто не ответил, то поставить статус rejected и отправить уведомление пациенту
        } else {
            //TODO отправить уведомления сестрам в округе, но первой поставить указанную медсестру
        }
        return null;
    }

    private void sendNotification() {

    }
}
