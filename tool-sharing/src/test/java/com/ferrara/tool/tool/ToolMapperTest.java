package com.ferrara.tool.tool;


import com.ferrara.tool.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ToolMapperTest {

    private ToolMapper mapper;

    @BeforeEach
    void beforeEach() {
        mapper = new ToolMapper();
    }

    @Test
    public void shouldMapToolRequestToTool(){

        // GIVEN
        ToolRequest request = new ToolRequest(
                1,
                "ClawHammer",
                "Tool description",
                "Good",
                "Handy Tool",
                true
        );

        // WHEN
        Tool tool = mapper.toTool(request);

        // THEN
        assertNotNull(request);
        assertEquals(request.name(),tool.getName());
        assertEquals(request.description(),tool.getDescription());
        assertEquals(request.condition(),tool.getCondition());
        assertEquals(request.available(),tool.isAvailable());

    }

    @Test
    public void shouldMapToolToToolResponse(){

        // GIVEN
        Tool tool = createDummyTool();

        // WHEN
        ToolResponse response = mapper.toToolResponse(tool);

        // THEN
        assertNotNull(response);
        assertEquals(response.getName(),tool.getName());
        assertEquals(response.getDescription(),tool.getDescription());
        assertEquals(response.getCondition(),tool.getCondition());
        assertEquals(response.isAvailable(),tool.isAvailable());

    }

    @Test
    public void shouldMapToolToToolResponseThrowAnException(){
        assertThrows(NullPointerException.class, () -> mapper.toToolResponse(createDummyToolWithoutUser()));
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

    private Tool createDummyToolWithoutUser() {
        Tool tool = new Tool();
        tool.setId(1);
        tool.setName("Cordless Drill");
        tool.setDescription("A powerful cordless drill for wood and metal.");
        tool.setCondition("Good");
        tool.setCategory("Power Tools");
        tool.setToolPicture("/src/assets/images/"+tool.getName().replace(" ",""));
        tool.setAvailable(true);
        tool.setArchived(false);

        return tool;
    }

}