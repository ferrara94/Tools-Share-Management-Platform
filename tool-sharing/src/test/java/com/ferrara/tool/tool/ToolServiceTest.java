package com.ferrara.tool.tool;

import com.ferrara.tool.file.FileStorageService;
import com.ferrara.tool.history.ToolTransactionHistoryRepository;
import com.ferrara.tool.user.User;
import com.ferrara.tool.utils.common.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.List;

class ToolServiceTest {

    @InjectMocks
    private ToolService service;

    @Mock
    private ToolMapper toolMapper;
    @Mock
    private ToolRepository repository;
    @Mock
    private ToolTransactionHistoryRepository transactionHistoryRepository;
    @Mock
    private FileStorageService fileStorageService;
    @Mock
    private Authentication authentication;
    @Mock
    private User user;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveTool(){

        // GIVEN
        ToolRequest request = new ToolRequest(
                1,
                "Cordless Drill",
                "A powerful cordless drill for wood and metal.",
                "Good",
                "Power Tools",
                true
        );
        Tool tool = createDummyTool(); // mock return object
        Tool savedTool = createDummyTool();
        savedTool.setId(1);

        // WHEN

        when(authentication.getPrincipal()).thenReturn(user);
        when(repository.save(tool)).thenReturn(savedTool);
        when(toolMapper.toTool(request)).thenReturn(tool);

        Integer id = service.save(request, authentication);

        // THEN
        assertEquals(id,tool.getId());

        verify(toolMapper, times(1)).toTool(request);
        verify(repository, times(1)).save(tool);
    }

    @Test
    public void shouldReturnAllTools(){

        // GIVEN
        int page = 0;
        int size = 5;
        Integer userId = 1;

        Tool tool = createDummyTool();
        ToolResponse toolResponse = new ToolResponse();

        List<Tool> toolList = List.of(tool);
        Page<Tool> tools = new PageImpl<>(toolList, PageRequest.of(page, size), 1);

        when(authentication.getPrincipal()).thenReturn(user);
        when(user.getId()).thenReturn(userId);
        when(repository.findAllAvailableTools(any(Pageable.class), eq(userId))).thenReturn(tools);
        when(toolMapper.toToolResponse(tool)).thenReturn(toolResponse);


        // WHEN
        PageResponse<ToolResponse> pageResponse = service.findAllTools(page,size, authentication);

        //THEN
        assertEquals(1, pageResponse.getContent().size());
        assertEquals(toolResponse, pageResponse.getContent().get(0));
        assertEquals(page, pageResponse.getNumber());
        assertEquals(size, pageResponse.getSize());
        assertEquals(1, pageResponse.getTotalElements());
        assertEquals(1, pageResponse.getTotalPages());
    }

    private Tool createDummyTool() {
        Tool tool = new Tool();
        tool.setId(1);
        tool.setName("Cordless Drill");
        tool.setDescription("A powerful cordless drill for wood and metal.");
        tool.setCondition("Good");
        tool.setCategory("Power Tools");
        tool.setToolPicture("/src/assets/images/"+tool.getName().replace(" ",""));
        tool.setAvailable(true);
        tool.setArchived(false);

        User owner = new User();
        owner.setFirstName("Mark");
        owner.setLastName("Rossi");

        tool.setOwner(owner);

        return tool;
    }

}