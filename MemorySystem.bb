Type Memory
    Field name$
    Field value%
End Type

Function InitMemories()
    Delete Each Memory
    ;DebugLog("init")
End Function

Function RecordMemory(memName$, val%)
    Local m.Memory
    For m.Memory = Each Memory
        If m\name = memName Then
            m\value = val
            Return
        EndIf
    Next
    m = New Memory
    m\name = memName
    m\value = val
End Function

Function GetMemory%(memName$)
    Local m.Memory
    For m.Memory = Each Memory
        If m\name = memName Then Return m\value
    Next
    Return 0
End Function

Function SaveMemories(f%)
    Local count% = 0
    Local m.Memory
    For m.Memory = Each Memory
        count = count + 1
    Next
    WriteInt(f, count)
    For m.Memory = Each Memory
        WriteString(f, m\name)
        WriteInt(f, m\value)
    Next
    ;DebugLog("save")
End Function

Function LoadMemories(f%)
    Delete Each Memory
    Local count% = ReadInt(f)
    For i = 1 To count
        Local m.Memory = New Memory
        m\name = ReadString(f)
        m\value = ReadInt(f)
    Next
    ;DebugLog("load")
End Function