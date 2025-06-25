package com.dewipuspitasari0020.linakost.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dewipuspitasari0020.linakost.ui.theme.bg
import com.dewipuspitasari0020.linakost.viewModel.PenginapViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.linakost.ui.theme.bgSecondary
import com.dewipuspitasari0020.linakost.ui.theme.textBlack
import com.dewipuspitasari0020.linakost.util.ViewModelFactory
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.dewipuspitasari0020.linakost.R

const val KEY_ID_PENGINAP = "id"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahPenginapScreen(
    navController: NavHostController,
    id: Int? = null
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: PenginapViewModel = viewModel(factory = factory)

    var fullName by remember { mutableStateOf("") }
    var numberRoom by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var checkIn by remember { mutableStateOf("") }
    var checkOut by remember { mutableStateOf("") }

    val currentUserId by viewModel.userId.collectAsStateWithLifecycle()

    val showCheckInDatePicker = remember { mutableStateOf(false) }
    val showCheckOutDatePicker = remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val initialDateMillis = remember { calendar.timeInMillis }
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    if (showCheckInDatePicker.value) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)
        DatePickerDialog(
            onDismissRequest = { showCheckInDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            checkIn = dateFormat.format(selectedDateMillis)
                        }
                        showCheckInDatePicker.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCheckInDatePicker.value = false }) {
                    Text("Cancel")
                }
            }
        ) {
            Box(modifier = Modifier.weight(1f)) {
                DatePicker(
                    state = datePickerState,
                )
            }
        }
    }

    if (showCheckOutDatePicker.value) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)
        DatePickerDialog(
            onDismissRequest = { showCheckOutDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            checkOut = dateFormat.format(selectedDateMillis)
                        }
                        showCheckOutDatePicker.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCheckOutDatePicker.value = false }) {
                    Text("Cancel")
                }
            }
        ) {
            Box(modifier = Modifier.weight(1f)) {
                DatePicker(
                    state = datePickerState,
                )
            }
        }
    }

    LaunchedEffect(id) {
        if (id != null) {
            val penginap = viewModel.getBarang(id)
            penginap?.let {
                fullName = it.fullName
                numberRoom = it.numberRoom.toString()
                address = it.address.toString()
                price = it.price.toString()
                checkIn = it.checkIn
                checkOut = it.checkOut
            }
        }
    }

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bg,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    if (id != null){
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 24.dp, bottom = 24.dp)
                ) {
                    val title = if (id == null)
                        stringResource(R.string.tambah_penginap)
                    else
                        stringResource(R.string.edit_penginap)

                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    val subtitle = if (id == null)
                        stringResource(R.string.subtitle_tambah)
                    else
                        stringResource(R.string.subtitle_edit)

                    Text(
                        text = subtitle,
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .weight(1f)
            ) {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Nama Lengkap Penginap", color = Color.Gray) },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = numberRoom,
                    onValueChange = { numberRoom = it },
                    label = { Text("Nomor Kamar", color = Color.Gray) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Alamat", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    minLines = 3,
                    maxLines = 5,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 90.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { newValue ->
                        if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                            price = newValue
                        }
                    },
                    label = { Text("Harga Sewa", color = Color.Gray) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = checkIn,
                    onValueChange = { checkIn = it },
                    label = { Text("Tanggal Check-in (DD/MM/YYYY)", color = Color.Gray) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showCheckInDatePicker.value = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Select Check-in Date")
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCheckInDatePicker.value = true }
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = checkOut,
                    onValueChange = { checkOut = it },
                    label = { Text("Tanggal Check-out (DD/MM/YYYY)", color = Color.Gray) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showCheckOutDatePicker.value = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Select Check-out Date")
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White, focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.Gray, focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White, containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCheckOutDatePicker.value = true }
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (fullName.isBlank() || numberRoom.isBlank() || address.isBlank() ||
                            price.isBlank() || checkIn.isBlank() || checkOut.isBlank()) {
                            Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        val priceDouble = price.toDoubleOrNull()
                        if (priceDouble == null || priceDouble <= 0) {
                            Toast.makeText(context, "Harga sewa tidak valid!", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val idUser = currentUserId
                        if (idUser == null || idUser == 0) {
                            Toast.makeText(context, "User ID tidak ditemukan. Mohon login kembali.", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        if (id == null) {
                            viewModel.addPenginap(
                                userId = idUser,
                                fullName = fullName,
                                numberRoom = numberRoom,
                                address = address,
                                price = priceDouble,
                                checkIn = checkIn,
                                checkOut = checkOut,
                                onSuccess = {
                                    Toast.makeText(context, "Data penginap berhasil disimpan!", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                },
                                onError = { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                                }
                            )
                        } else {
                            viewModel.updatePenginap(
                                id = id,
                                fullName = fullName,
                                numberRoom = numberRoom,
                                address = address,
                                price = priceDouble,
                                checkIn = checkIn,
                                checkOut = checkOut,
                                onSuccess = {
                                    Toast.makeText(context, "Data penginap berhasil diperbarui!", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                },
                                onError = { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                                }
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = bgSecondary,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Simpan Data Penginap",
                        color = textBlack,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            if(id != null && showDialog){
                DisplayAlertDialog(
                    onDismissRequest = { showDialog = false }) {
                    showDialog = false
                    viewModel.delete(id)
                    navController.popBackStack()
                }
            }
        }
    }
}


@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.other),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.delete))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddPenginapScreen() {
    TambahPenginapScreen(rememberNavController())
}